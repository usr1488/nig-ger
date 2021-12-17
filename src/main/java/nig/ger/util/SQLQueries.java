package nig.ger.util;

public interface SQLQueries {
    String CREATE_TABLE_PLACES = "CREATE TABLE places (" +
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
}
