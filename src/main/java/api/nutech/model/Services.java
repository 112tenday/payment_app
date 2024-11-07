package api.nutech.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "services")
@Getter
@Setter
public class Services {
    @Id
    @GeneratedValue
    private UUID id;

    private String serviceName;
    private String description;
}
