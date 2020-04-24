package com.mynic.warehouse.constant;

public enum RoleType {
    ADMIN("ADMIN"),
    USER("USER");
    public String role;
    RoleType (String role) {
        this.role = role;
    }
}
