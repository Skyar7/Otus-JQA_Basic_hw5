package data;

public enum ContactMethodData {
    FACEBOOK("facebook"),
    VK("vk"),
    OK("ok"),
    SKYPE("skype"),
    VIBER("viber"),
    TELEGRAM("telegram"),
    WHATSUP("whatsapp"),
    HABR("habr");

    private String name;

    ContactMethodData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}