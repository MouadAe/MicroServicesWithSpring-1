package com.enset.authentificationservice.security.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enset.authentificationservice.security.JWTUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
    private AccountService accountService;
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }
    @Operation(summary = "Get All users")
    @GetMapping(path = "/users")
//    @PreAuthorize("hasAuthority('USER')")
    @PreAuthorize("permitAll()")
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
    @Operation(summary = "Refresh the jwt token")
    @PostMapping("/refreshToken")
    public Map<String, String> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(JWTUtil.AUTH_HEADER);
        if (token != null && token.startsWith(JWTUtil.TOKEN_PREFIX)) {
            try {
                String jwtRefreshToken = token.substring(JWTUtil.TOKEN_PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT
                        .create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim(
                                "roles",
                                appUser.getAppRoles().stream()
                                        .map(AppRole::getRoleName)
                                        .collect(Collectors.toList())
                        )
                        .sign(algorithm);

                Map<String, String> accessToken = new HashMap<>();
                accessToken.put("Access_Token", jwtAccessToken);
                accessToken.put("Refresh_Token", jwtRefreshToken);
                return accessToken;
            } catch (TokenExpiredException e) {
                response.setHeader("Error-Message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        throw new RuntimeException("Bad Refresh Token");
    }

    @Operation(summary = "Dispaly the user profile")
    @GetMapping(path = "/profile")
    public AppUser profile(Principal user){
        return accountService.loadUserByUsername(user.getName());
    }
}

@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
