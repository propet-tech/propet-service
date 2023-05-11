package br.com.senai.propetservice.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class PrincipalUtils {

    public static boolean userHasAuthority(Collection<GrantedAuthority> authorities, String authority) {
        return authorities.stream().anyMatch(
                ga -> ga.getAuthority().equals(authority));
    }
}
