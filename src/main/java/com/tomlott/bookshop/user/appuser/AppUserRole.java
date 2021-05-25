package com.tomlott.bookshop.user.appuser;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


public enum AppUserRole {
    USER(Sets.newHashSet(AppUserPermission.USER_READ,
            AppUserPermission.USER_WRITE)),
    ADMIN(Sets.newHashSet(AppUserPermission.USER_READ,
                            AppUserPermission.USER_WRITE,
                                AppUserPermission.ADMIN_READ,
                                    AppUserPermission.ADMIN_WRITE));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions(){ return permissions; }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(a -> new SimpleGrantedAuthority(a.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
