package by.academy.model;

public enum Users {
    USER("user"),
    MANAGER("manager"),
    ADMIN("admin");

    private final String userRole;

    Users(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
