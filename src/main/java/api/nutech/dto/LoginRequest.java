package api.nutech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {

    @Email(message = "Paramter email tidak sesuai format")
    @NotBlank(message = "Email tidak boleh kosong")
    @Schema(description = "email ", example = "iwancoba@gmail.com")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 8, message = "Password minimal 8 karakter")
    @Schema(description = "password", example = "123abc123abc")
    private String password;
}
