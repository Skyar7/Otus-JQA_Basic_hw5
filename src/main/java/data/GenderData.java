package data;

public enum GenderData {
    MALE("m", "1"),
    FEMALE("f", "2");
    private String value = "";
    private String index = "";

    GenderData(String value, String index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public String getIndex() {
        return index;
    }
}