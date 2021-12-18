package nig.ger.controller;

import nig.ger.dao.PlaceDAO;
import nig.ger.entity.Place;
import nig.ger.entity.PlaceCategory;
import nig.ger.util.SQLQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class PlaceController {
    private JdbcTemplate jdbcTemplate;
    private PlaceDAO placeDAO = new PlaceDAO();

    @GetMapping("/place/{placeId}")
    public String getPlace(@RequestParam Long placeId,
                           @RequestParam String name,
                           @RequestParam String country,
                           @RequestParam String city,
                           @RequestParam String location,
                           @RequestParam String description,
                           @RequestParam String category,
                           @RequestParam int rate,
                           Model placeModel) {

        List<Place> places = new ArrayList<>();

        placeDAO.selectAllPlacesById(placeId, places);

        placeModel.addAttribute("niggerList", places);

        return "place";
    }
}
