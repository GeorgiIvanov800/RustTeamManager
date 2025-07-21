package org.rtm.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final String clientId;

    public KeycloakRealmRoleConverter(String clientId) {
        this.clientId = clientId;
    }

    @Override
   @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        final Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaim("resource_access");

        if (resourceAccess == null || resourceAccess.isEmpty()) {
            return List.of();
        }

        Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get(clientId);
        List<String> roles = (List<String>) clientAccess.get("roles");


        return roles.stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

    }

}
