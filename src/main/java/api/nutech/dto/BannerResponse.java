package api.nutech.dto;

import lombok.Getter;
import lombok.Setter;

public class BannerResponse {


        private String bannerName;
        private String bannerImage;
        private String description;


        public BannerResponse(String bannerName, String bannerImage, String description) {
            this.bannerName = bannerImage;
            this.bannerImage = bannerName;
            this.description = description;
        }

    }


