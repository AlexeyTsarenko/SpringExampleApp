package com.springExampleApp.security;

import com.springExampleApp.entities.Role;
import com.springExampleApp.entities.Status;
import com.springExampleApp.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public static JwtUser createByUserEntity(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                convertRolesToAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> convertRolesToAuthorities(List<Role> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }
}
