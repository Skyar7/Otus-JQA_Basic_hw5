package data;

public enum EnglishLvlsData {

    BEGINNER("Начальный уровень (Beginner)"),
    ELEMENTARY("Элементарный уровень (Elementary)"),
    PREINTERMEDIATE("Ниже среднего (Pre-Intermediate)"),
    INTERMEDIATE("Средний (Intermediate)"),
    UPPER_INTERMEDIATE("Выше среднего (Upper Intermediate)"),
    ADVANCED("Продвинутый (Advanced)"),
    MASTERY("Супер продвинутый (Mastery)");

    private String name;

    EnglishLvlsData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}