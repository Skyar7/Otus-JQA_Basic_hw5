package data.cities;

public enum CountriesData implements ICityData {
    BELARUS("Республика Беларусь"),
    KAZAHSTAN("Казахстан"),
    RUSSIA("Россия");

    private String name;

    CountriesData(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CountriesData getCountryData() {
        return null;
    }
}
