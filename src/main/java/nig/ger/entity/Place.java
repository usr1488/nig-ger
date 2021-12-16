package nig.ger.entity;

public class Place {
    private long placeId;
    private String name;
    private String country;
    private String city;
    private String location;
    private String description;
    private Category category;

    public Place() {
    }

    public Place(long placeId, String name, String country, String city, String location, String description, Category category) {
        this.placeId = placeId;
        this.name = name;
        this.country = country;
        this.city = city;
        this.location = location;
        this.description = description;
        this.category = category;
    }

    public Place(String name, String country, String city, String location, String description, Category category) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.location = location;
        this.description = description;
        this.category = category;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%d\n%s\n%s\n%s\n%s\n%s\n%s", placeId, name, country, city, location, description, category.getCategory());
    }
}
