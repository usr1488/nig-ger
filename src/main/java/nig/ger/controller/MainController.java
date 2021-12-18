package nig.ger.controller;

import nig.ger.entity.PlaceCategory;
import nig.ger.entity.Place;
import nig.ger.util.SQLQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private JdbcTemplate jdbcTemplate;

    public MainController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        jdbcTemplate.execute(SQLQueries.CREATE_TABLE_PLACES);
    }

    @PostMapping("/")
    public String addPlace(@RequestParam String name,
                           @RequestParam String country,
                           @RequestParam String city,
                           @RequestParam String location,
                           @RequestParam String description,
                           @RequestParam String category) {
        jdbcTemplate.update(SQLQueries.INSERT_INTO_PLACES, ps -> {
            ps.setString(1, name);
            ps.setString(2, country);
            ps.setString(3, city);
            ps.setString(4, location);
            ps.setString(5, description);
            ps.setString(6, category);
        });

        return "redirect:/";
    }

    @GetMapping("/")
    public String getPlace(Model model) {
        List<Place> places = new ArrayList<>();

        jdbcTemplate.query(
                SQLQueries.SELECT_FROM_PLACES,
                (RowCallbackHandler) resultSet -> places.add(
                        Place.builder()
                                .placeId(resultSet.getLong("place_id"))
                                .name(resultSet.getString("name"))
                                .country(resultSet.getString("country"))
                                .city(resultSet.getString("city"))
                                .location(resultSet.getString("location"))
                                .description(resultSet.getString("description"))
                                .placeCategory(PlaceCategory.valueOf(resultSet.getString("category").toUpperCase()))
                                .build()
                )
        );

        model.addAttribute("niggerList", places);

        return "main";
    }
}
