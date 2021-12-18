package nig.ger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlaceController {
    @GetMapping("/place/{placeId}")
    public String getPlace(@PathVariable(required = false) long placeId) {
        System.out.println("Place id: " + placeId);
        return "place";
    }
}
