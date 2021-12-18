package nig.ger.controller;

import nig.ger.entity.Place;
import nig.ger.entity.PlaceCategory;
import nig.ger.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    private PlaceService placeService;

    public MainController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/")
    public String addPlace(@RequestParam String name,
                           @RequestParam String country,
                           @RequestParam String city,
                           @RequestParam String address,
                           @RequestParam String description,
                           @RequestParam String placeCategory) {
        placeService.savePlace(
                Place.builder()
                        .name(name)
                        .country(country)
                        .city(city)
                        .address(address)
                        .description(description)
                        .placeCategory(PlaceCategory.valueOf(placeCategory.toUpperCase()))
                        .build()
        );

        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView getAllPlaces() {
        return new ModelAndView("main", "niggerList", placeService.getAllPlaces());
    }
}
