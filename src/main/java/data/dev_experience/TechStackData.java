package data.dev_experience;

public enum TechStackData {
    C("C"),
    CPLUS("C++"),
    JAVA("Java"),
    CSHARP("C#"),
    GO("Go"),
    ANDROID("Android"),
    PYTHON("Python");

    private String name;

    TechStackData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}