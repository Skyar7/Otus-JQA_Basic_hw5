package data;

public enum BrowsersData {
    CHROME("chrome"),
    FIREFOX("firefox");

    private String name;

    BrowsersData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
