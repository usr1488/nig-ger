package nig.ger.controller;

import nig.ger.entity.Category;
import nig.ger.entity.Place;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    private List<Place> places = new ArrayList<>();

//    @GetMapping("/")
//    public String main() {
//        return "redirect:/niggers";
//    }

    @PostMapping("/")
    public String addPlace(@RequestParam String name,
                           @RequestParam String country,
                           @RequestParam String city,
                           @RequestParam String location,
                           @RequestParam String description,
                           @RequestParam String category,
                           @RequestParam int rate) {
        Place place = new Place(name, country, city, location,description, Arrays.stream(Category.values())
                .filter(categ -> categ.getCategory().equalsIgnoreCase(category))
                .findFirst()
                .orElseThrow(RuntimeException::new), rate);
        places.add(place);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getPlace(Model model) {
        List<Place> niggerList = places;
        if (niggerList.isEmpty()) {
            Place place = new Place(1, "Nigger", "Niggeria", "Black city", "Location", "Desc", Category.ABANDONED_PLACES, 1);
            niggerList.add(place);
        }
        model.addAttribute("niggerList", niggerList);
        return "main";
    }
}
