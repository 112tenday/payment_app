package api.nutech.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "banners")
@Getter
@Setter
public class Banner {
    @Id
    @GeneratedValue
    private UUID id;
    private String bannerName;
    private String bannerImage;
    private String description;
}
