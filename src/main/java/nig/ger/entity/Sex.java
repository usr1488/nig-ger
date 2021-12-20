package nig.ger.entity;

public enum Sex {
    HUMAN("Human"),
    FEMALE("Female");

    private final String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
