package api.nutech.repository;

import api.nutech.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BannerRepository extends JpaRepository<Banner, UUID> {
}
