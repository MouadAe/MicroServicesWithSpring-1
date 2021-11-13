package com.enset.authentificationservice.security.web;

import com.enset.authentificationservice.security.entitis.AppRole;
import com.enset.authentificationservice.security.entitis.AppUser;
import com.enset.authentificationservice.security.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private AccountService accountService;
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }
    @Operation(summary = "Get All users")
    @GetMapping(path = "/users")
    @PreAuthorize("hasAuthority('USER')")
    public List<AppUser> appUsers() {
        return accountService.listUsers();
    }

    @Operation(summary = "Add new user")
    @PostMapping(path = "/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser(@RequestBody AppUser appUser) {
        return accountService.addNewUser(appUser);
    }

    @Operation(summary = "Add new role")
    @PostMapping(path = "/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AppRole saveRole(@RequestBody AppRole appRole) {
        return accountService.addNewRole(appRole);
    }

    @Operation(summary = "Add role to user")
    @PostMapping(path = "/addRoleToUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }
}

@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
