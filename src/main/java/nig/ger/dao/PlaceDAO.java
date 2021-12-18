package nig.ger.dao;

import nig.ger.entity.Place;
import nig.ger.entity.PlaceCategory;
import nig.ger.util.SQLQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceDAO {
    private JdbcTemplate jdbcTemplate;

    public PlaceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Place> selectAllPlacesById(Long placeId) {
        List<Place> places = new ArrayList<>();

        jdbcTemplate.query(
                SQLQueries.SELECT_FROM_PLACES_WHERE_ID,
                (RowCallbackHandler) resultSet -> places.add(
                        Place.builder()
                                .placeId(resultSet.getLong("place_id"))
                                .name(resultSet.getString("name"))
                                .country(resultSet.getString("country"))
                                .city(resultSet.getString("city"))
                                .location(resultSet.getString("location"))
                                .description(resultSet.getString("description"))
                                .placeCategory(PlaceCategory.valueOf(resultSet.getString("category")))
                                .build()
                ),
                placeId
        );

        return places;
    }
}
