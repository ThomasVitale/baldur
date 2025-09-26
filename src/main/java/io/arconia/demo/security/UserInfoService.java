package io.arconia.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.hilla.BrowserCallable;

import jakarta.annotation.security.PermitAll;

@BrowserCallable
public class UserInfoService {

	@PermitAll 
    public UserInfo getUserInfo() {
        var auth = SecurityContextHolder.getContext().getAuthentication(); 
        var authorities = auth.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .toList();
        return new UserInfo(auth.getName(), authorities); 
    }

}

record UserInfo(String name, Collection<String> authorities) {}
