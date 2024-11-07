package api.nutech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateProfileRequest {
    private String email;
    @Schema(description = "first_name",example = "Iwan Edit")
    private String firstName;
    @Schema(description = "last_name",example = "Gunawan_Edit")
    private String lastName;
    private String profile_image;
}
