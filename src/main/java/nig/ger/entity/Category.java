package nig.ger.entity;

public enum Category {
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

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
