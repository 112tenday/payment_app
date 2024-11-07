package api.nutech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateProfileImageRequest {

    @Schema(description = "Profile image file (only JPEG or PNG)", required = true)
    private MultipartFile file;

    public UpdateProfileImageRequest(MultipartFile file) {
        this.file = file;
    }
}
