package nig.ger.controller;

import nig.ger.entity.Place;
import nig.ger.entity.PlaceCategory;
import nig.ger.service.PlaceService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Controller
public class HomeController {
    private static final String IMAGE_LOCATION = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("static/img/")
    ).getPath();
    private PlaceService placeService;

    public HomeController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addPlace(@RequestParam String name,
                           @RequestParam String country,
                           @RequestParam String city,
                           @RequestParam String address,
                           @RequestParam String description,
                           @RequestParam String placeCategory,
                           @RequestParam MultipartFile img) throws IOException {
        long id = placeService.savePlace(
                Place.builder()
                        .name(name)
                        .country(country)
                        .city(city)
                        .address(address)
                        .description(description)
                        .placeCategory(PlaceCategory.valueOf(placeCategory.toUpperCase()))
                        .build()
        ).getPlaceId();

        ImageIO.write(ImageIO.read(img.getInputStream()), "jpg", new File(IMAGE_LOCATION + id + ".jpg"));

        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView getAllPlaces() {
        return new ModelAndView("main", "niggerList", placeService.getAllPlaces());
    }
}
