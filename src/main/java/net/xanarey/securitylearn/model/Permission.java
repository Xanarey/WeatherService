package net.xanarey.securitylearn.model;

public enum Permission {
    DEV_ADMIN("dev:admin"),
    DEV_USER("dev:user");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
