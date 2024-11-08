package api.nutech.controller;

import api.nutech.dto.BannerResponse;
import api.nutech.service.BannerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/information")
@Tag(name = "Module Information")
@CrossOrigin("*")
public class InformationController {

    @Autowired
    private BannerService bannerService;

    @GetMapping ("/banner")
    public ResponseEntity<List<BannerResponse>> getAllBanners()
    {
        List<BannerResponse> bannerResponses = bannerService.getAllBanners();
        return ResponseEntity.ok(bannerResponses);
    }


    @GetMapping("/services")
    public String getServices() {
        return "Service list";
    }
}
