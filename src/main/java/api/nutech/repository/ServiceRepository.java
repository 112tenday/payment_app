package api.nutech.repository;

import api.nutech.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Services, UUID> {
}
