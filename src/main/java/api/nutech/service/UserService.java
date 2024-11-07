package api.nutech.service;

import api.nutech.dto.RegistrationRequest;
import api.nutech.model.User;
import api.nutech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;  // Import PasswordEncoder, bukan BCryptPasswordEncoder
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegistrationRequest request) {
        // Cek emailnya udah dipake apa belum
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email sudah digunakan";
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirst_name());
        user.setLastName(request.getLast_name());
        user.setPassword(encodedPassword);
        user.setProfileImage(null);
        userRepository.save(user);
        return null;
    }

    public boolean validateUser(String email, String rawPassword) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(rawPassword, user.getPassword()); // Validasi password
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User updateProfile(String email, String firstName, String lastName) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepository.save(user);
    }

    public User updateProfileImage(String email, MultipartFile file) throws IOException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        String profileImageUrl = saveProfileImage(file);

        user.setProfileImage(profileImageUrl);
        return userRepository.save(user);
    }

    private String saveProfileImage(MultipartFile file) throws IOException {
        // Save the image to the server or cloud storage and return the access URL
        String fileName = "profile-updated.jpeg"; // Name of the saved file
        return "https://yoururlapi.com/" + fileName;
    }

}
