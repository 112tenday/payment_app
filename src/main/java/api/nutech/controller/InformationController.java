package api.nutech.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/information")
@Tag(name = "Module Information")
public class InformationController {

    @GetMapping("/banner")
    public String getBanner() {
        return "Banner data";
    }

    @GetMapping("/services")
    public String getServices() {
        return "Service list";
    }
}
