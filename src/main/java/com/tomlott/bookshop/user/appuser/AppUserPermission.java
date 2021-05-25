package com.tomlott.bookshop.user.appuser;

public enum AppUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    AppUserPermission(String permission){
        this.permission = permission;
    }
    public String getPermission(){return permission;}
}
