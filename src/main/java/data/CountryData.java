package data;

public enum CountryData {
    RUSSIA("Россия");

    private String name;

    CountryData(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
