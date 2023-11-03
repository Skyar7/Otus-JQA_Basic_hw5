package data.dev_experience;

public enum TechStackData {
    C("1"),
    CPLUS("2"),
    JAVA("3"),
    CSHARP("4"),
    GO("5"),
    ANDROID("7"),
    PYTHON("10");

    private String name;

    TechStackData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
