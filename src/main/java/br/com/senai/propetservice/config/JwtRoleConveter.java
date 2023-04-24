package br.com.senai.propetservice.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;

public class JwtRoleConveter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
            LinkedTreeMap<String, ArrayList<String>> object = source.getClaim("realm_access");
            ArrayList<String> roles = object.get("roles");
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (String role : roles) {
                var authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantedAuthorities.add(authority);
            }
            
            return grantedAuthorities;
    }
}
