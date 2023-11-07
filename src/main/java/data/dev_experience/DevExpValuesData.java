package data.dev_experience;

public enum DevExpValuesData {
    JUST_BEGIN("Только начал"),
    ONE_YEAR("1 год"),
    TWO_YEARS("2 года"),
    THREE_FIVE_YEARS("3-5 лет"),
    FIVE_SEVEN_YEARS("5-7 лет"),
    SEVEN_TEN_YEARS("7-10 лет"),
    MORE_THAN_TEN_YEARS("Более 10 лет");

    private String name;

    DevExpValuesData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}