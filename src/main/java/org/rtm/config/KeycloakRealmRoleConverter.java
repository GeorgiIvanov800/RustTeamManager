package org.rtm.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
   @Override
   @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims();

        if (realmAccess == null || realmAccess.isEmpty()) {
            return List.of();
        }

        Map<String, Object> realmRoles = (Map<String, Object>) realmAccess.get("realm_access");

        return ((List<String>) realmRoles.get("roles")).stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

}
