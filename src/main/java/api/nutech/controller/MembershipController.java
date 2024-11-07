package api.nutech.controller;

import api.nutech.dto.*;
import api.nutech.model.User;
import api.nutech.service.JwtService;
import api.nutech.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/membership")
@Tag(name = "Module Membership")
public class MembershipController {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid RegistrationRequest request) {
        String result = userService.register(request);

        if (result == null) {
            ApiResponse response = new ApiResponse(0, "Registrasi berhasil silahkan login", null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(102, result, null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
        boolean isValidUser = userService.validateUser(request.getEmail(), request.getPassword());

        if (!isValidUser) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ApiResponse(103, "Username atau password salah", null)
            );
        }

        String token = jwtService.generateToken(request.getEmail());

        ApiResponse response = new ApiResponse(0, "Login Sukses", Map.of("token", token));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        String token = null;

        // Ambil email dari token
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ApiResponse(108, "Token tidak valid atau kadaluwarsa", null)
            );
        }

        // Ambil data profil pengguna berdasarkan email
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(104, "User tidak ditemukan", null)
            );
        }

        // kasih respon kalo usernya ada
        ApiResponse response = new ApiResponse(0, "Sukses", Map.of(
                "email", user.getEmail(),
                "first_name", user.getFirstName(),
                "last_name", user.getLastName(),
                "profile_image", user.getProfileImage() != null ? user.getProfileImage() : "https://yoururlapi.com/profile.jpeg"
        ));
        return ResponseEntity.ok(response);
    }


    @PutMapping("/profile/update")
    public ResponseEntity<ApiResponse> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest) {

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Updating profile for email: " + email);

        User updatedUser = userService.updateProfile(email, updateProfileRequest.getFirstName(), updateProfileRequest.getLastName());

        ProfileResponse profileResponse = new ProfileResponse(
                updatedUser.getEmail(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updateProfileImage()
        );

        ApiResponse response = new ApiResponse(0, "Update Profile berhasil", profileResponse);
        return ResponseEntity.ok(response);
    }



    @PutMapping(value = "/profile/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation
    public ResponseEntity<ApiResponse> uploadProfileImage(
            @Parameter( required = false)
            @RequestPart(value = "file", required = false) MultipartFile file) {

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(102, "Format Image tidak sesuai", null));
        }

        String contentType = file.getContentType();
        if (contentType == null ||
                (!contentType.equals(MediaType.IMAGE_JPEG_VALUE) && !contentType.equals(MediaType.IMAGE_PNG_VALUE))) {
            return ResponseEntity.badRequest().body(new ApiResponse(102, "Format Image tidak sesuai", null));
        }

        try {
            User updatedUser = userService.updateProfileImage(email, file);

            ProfileResponse profileResponse = new ProfileResponse(
                    updatedUser.getEmail(),
                    updatedUser.getFirstName(),
                    updatedUser.getLastName(),
                    updatedUser.getProfileImage()
            );

            return ResponseEntity.ok(new ApiResponse(0, "Update Profile Image berhasil", profileResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(108, "Token tidak tidak valid atau kadaluwarsa", null));
        }
    }
    public String updateProfileImage() {
        return "Update Profile Image berhasil";
    }

}
