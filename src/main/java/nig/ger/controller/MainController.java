package nig.ger.controller;

import nig.ger.entity.Place;
import nig.ger.util.ConnectionPool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private ConnectionPool connectionPool;

    public MainController(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;

        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE places (name VARCHAR NOT NULL, country VARCHAR NOT NULL, city VARCHAR NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/")
    public String addPlace(@RequestParam String name,
                           @RequestParam String country,
                           @RequestParam String city,
                           @RequestParam String location,
                           @RequestParam String description,
                           @RequestParam String category) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO places (name, country, city) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, city);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("/")
    public String getPlace(Model model) {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            List<Place> places = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM places");

            while (resultSet.next()) {
                System.out.printf(
                        "name: %s\ncountry: %s\ncity: %s\n",
                        resultSet.getString("name"),
                        resultSet.getString("country"),
                        resultSet.getString("city")
                );

                places.add(
                        Place.builder()
                                .name(resultSet.getString("name"))
                                .country(resultSet.getString("country"))
                                .city(resultSet.getString("city"))
                                .build()
                );
            }

            model.addAttribute("niggerList", places);

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "main";
    }
}
