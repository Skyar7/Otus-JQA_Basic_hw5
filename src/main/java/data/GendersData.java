package data;

public enum GendersData {
    MALE("m"),
    FEMALE("f");
    private String name = "";

    GendersData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}