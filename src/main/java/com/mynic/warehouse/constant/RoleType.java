package com.mynic.warehouse.constant;

public enum RoleType {
    ADMIN(1),
    USER(2);
    public int role;
    RoleType (int role) {
        this.role = role;
    }

    public static int getRole(String s) {
        if (s.equals("ADMIN"))
            return ADMIN.role;
        else if (s.equals("USER"))
            return USER.role;
        return 0;
    }
}
