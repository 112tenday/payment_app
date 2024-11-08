package api.nutech.service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import api.nutech.dto.BannerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nutech.model.Banner;
import api.nutech.repository.BannerRepository;

@Service
public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    public List<BannerResponse> getAllBanners() {
        List<Banner> banners = bannerRepository.findAll();

        return banners.stream()
                .map(this::convertToBannerResponse)
                .collect(Collectors.toList());
    }

    private BannerResponse convertToBannerResponse(Banner banner) {
        return new BannerResponse(banner.getBannerName(), banner.getBannerImage(), banner.getDescription());
    }
}