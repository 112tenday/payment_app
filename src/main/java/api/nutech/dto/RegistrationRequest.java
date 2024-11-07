package api.nutech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationRequest {


    @Email(message = "Parameter email tidak sesuai format")
    @NotBlank(message = "Parameter email harus di isi")
    @Schema(description = "email ", example = "iwancoba@gmail.com")
    private String email;

    @Schema(description = "first_name",example = "Iwan")
    private String first_name;

    @Schema(description = "last_name",example = "Gunawan")
    private String last_name;

    @NotBlank(message = "Parameter email harus di isi")
    @Size(min = 8, message = "Password minimal 8 karakter")
    @Schema(description = "password", example = "123abc123abc")
    private String password;



}
