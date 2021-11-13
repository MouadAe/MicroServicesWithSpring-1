package com.enset.authentificationservice.security.service;

import com.enset.authentificationservice.security.entitis.AppRole;
import com.enset.authentificationservice.security.entitis.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
