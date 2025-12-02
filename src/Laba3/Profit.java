package Laba3;

public enum Profit {
    PROFITABLE("поездка выгодная"),
    UNPROFITABLE("поездка невыгодная");

    private final String description;

    Profit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}