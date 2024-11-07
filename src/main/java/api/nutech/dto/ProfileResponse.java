package api.nutech.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String profileImage;

    public ProfileResponse(String email, String firstName, String lastName, String profileImage) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
    }

}
