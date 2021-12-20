package nig.ger.controller;

import nig.ger.entity.Place;
import nig.ger.entity.PlaceCategory;
import nig.ger.service.PlaceService;
import nig.ger.util.Constants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController {
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
                           @RequestParam MultipartFile placeImage) throws IOException {
        placeService.savePlace(
                Place.builder()
                        .name(name)
                        .country(country)
                        .city(city)
                        .address(address)
                        .description(description)
                        .placeCategory(PlaceCategory.valueOf(placeCategory.toUpperCase()))
                        .build(),
                placeImage
        );

        return Constants.ROOT_REDIRECT;
    }

    @GetMapping("/")
    public ModelAndView allPlaces() {
        return new ModelAndView("main", "niggerList", placeService.getAllPlaces());
    }

    @GetMapping("/place/{placeId}")
    public ModelAndView placeDetails(@PathVariable long placeId) {
        return new ModelAndView("place", "niggerList", placeService.getPlaceById(placeId));
    }
}
