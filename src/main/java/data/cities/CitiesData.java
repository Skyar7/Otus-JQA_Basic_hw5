package data.cities;

public enum CitiesData implements ICityData {
    GOMEL("Гомель", CountriesData.BELARUS),
    MINSK("Минск", CountriesData.BELARUS),

    ASTANA("Астана", CountriesData.KAZAHSTAN),
    KARAGANDA("Караганда", CountriesData.KAZAHSTAN),

    ABAKAN("Абакан", CountriesData.RUSSIA),
    MOSCOW("Москва", CountriesData.RUSSIA),
    KRASNOYARSK("Красноярск", CountriesData.RUSSIA);


    private String name;
    private CountriesData countriesData;

    CitiesData(String name, CountriesData countriesData) {
        this.name = name;
        this.countriesData = countriesData;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CountriesData getCountryData() {
        return countriesData;
    }
}
