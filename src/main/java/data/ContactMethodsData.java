package data;

public enum ContactMethodsData {
    FACEBOOK("Facebook"),
    VK("VK"),
    OK("OK"),
    SKYPE("Skype"),
    VIBER("Viber"),
    WHATSUP("WhatsApp"),
    HABR("Habr");

    private String name;

    ContactMethodsData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}