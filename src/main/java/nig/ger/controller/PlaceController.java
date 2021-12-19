package nig.ger.controller;

import nig.ger.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlaceController {
    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/place/{placeId}")
    public ModelAndView place(@PathVariable long placeId) {
        return new ModelAndView("place", "niggerList", placeService.getPlaceById(placeId));
    }
}
