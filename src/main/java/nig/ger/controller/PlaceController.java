package nig.ger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaceController {
    @GetMapping("/place/{placeId}")
    public String getPlace(@RequestParam long placeId,
                           @RequestParam String name,
                           @RequestParam String country,
                           @RequestParam String city,
                           @RequestParam String location,
                           @RequestParam String description,
                           @RequestParam String category,
                           @RequestParam int rate,
                           Model placeModel) {
        return "place";
    }
}
