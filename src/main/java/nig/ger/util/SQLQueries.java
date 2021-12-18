package nig.ger.util;

public interface SQLQueries {
    String CREATE_TABLE_PLACES = "CREATE TABLE places (" +
            "place_id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR NOT NULL, " +
            "country VARCHAR NOT NULL, " +
            "city VARCHAR NOT NULL, " +
            "location VARCHAR NOT NULL, " +
            "description VARCHAR NOT NULL, " +
            "category VARCHAR NOT NULL" +
            ")";
    String INSERT_INTO_PLACES = "INSERT INTO places (" +
            "name, " +
            "country, " +
            "city, " +
            "location, " +
            "description, " +
            "category" +
            ") " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    String SELECT_FROM_PLACES = "SELECT * FROM places";
    String SELECT_FROM_PLACES_WHERE_ID = "SELECT * FROM places WHERE place_id = :placeId";
}
