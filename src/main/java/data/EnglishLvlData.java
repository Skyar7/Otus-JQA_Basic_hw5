package data;

public enum EnglishLvlData {

    BEGINNER("Начальный уровень"),
    ELEMENTARY("Элементарный уровень"),
    PREINTERMEDIATE("Ниже среднего"),
    INTERMEDIATE("Средний"),
    UPPER_INTERMEDIATE("Выше среднего"),
    ADVANCED("Продвинутый"),
    MASTERY("Супер продвинутый");

    private String name;

    EnglishLvlData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}