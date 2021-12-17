package nig.ger.entity;

public enum PlaceCategory {
    SIGHTS("Sights"),
    PARKS("Parks"),
    RESERVES("Reserves"),
    ZOO("Zoo"),
    ABANDONED_PLACES("Abandoned places"),
    UNDERGROUND_PLACES("Underground places"),
    CAFE("Cafe"),
    CINEMA("Cinema"),
    RESTAURANT("Restaurant");

    private final String category;

    PlaceCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
