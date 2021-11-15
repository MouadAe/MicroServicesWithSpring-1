package com.enset.authentificationservice.security.service;

import com.enset.authentificationservice.security.entitis.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getAppRoles()
                        .stream()
                        .map( role -> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList())
        );

    }
}
