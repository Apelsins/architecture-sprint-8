package com.yandex.API;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        if (realmAccess == null || realmAccess.isEmpty()) {
            return grantedAuthorities;
        }

        List<String> roles = (List<String>) realmAccess.get("roles");
        if (roles == null) {
            return grantedAuthorities;
        }

        for (String role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return grantedAuthorities;
    }
}
