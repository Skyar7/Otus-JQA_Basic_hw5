package data;

public enum CountryData implements ICityData {
    RUSSIA("Россия");

    private String name;

    CountryData(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CountryData getCountryData() {
        return null;
    }
}
