package data;

public enum RussianCityData implements ICityData {
    MOSCOW("Москва", CountryData.RUSSIA);

    private String name;
    private CountryData countryData;

    RussianCityData(String name, CountryData countryData) {
        this.name = name;
        this.countryData = countryData;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CountryData getCountryData() {
        return countryData;
    }
}
