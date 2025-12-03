package Laba5;

enum OlympiadLevel {
    SCHOOL("Школьная"),
    CITY("Городская"),
    REGIONAL("Областная");

    private final String description;

    OlympiadLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
