package br.com.senai.propetservice.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

@Component
@Slf4j
public class JwtRoleConveter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        try {
            JSONObject object = source.getClaim("realm_access");
            String rolesString = object.getAsString("roles");
            List<String> roles = mapper.readValue(rolesString, new TypeReference<List<String>>(){});
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (String role : roles) {
                var authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantedAuthorities.add(authority);
            }
            
            return grantedAuthorities;
        } catch (JsonProcessingException e) {
            log.warn("KeyCloak roles converter failed!");
            return Collections.emptyList();
        }
    }
}
