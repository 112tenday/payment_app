package api.nutech.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String profileImage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
