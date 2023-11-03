package data.dev_experience;

public enum DevExpValuesData {
    JUSTBEGIN("Только начал"),
    ONEYEAR("1 год"),
    TWOYEARS("2 года"),
    MORETHAN10YEARS("Более 10 лет");

    private String name;

    DevExpValuesData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
