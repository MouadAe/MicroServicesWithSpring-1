package com.enset.authentificationservice.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.enset.authentificationservice.security.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(JWTUtil.AUTH_HEADER);
        if (token == null || request.getServletPath().equals("/refreshToken")) {
            filterChain.doFilter(request, response);
        } else {
            if (token.startsWith(JWTUtil.TOKEN_PREFIX)) {
                try {
                    String jwt = token.substring(JWTUtil.TOKEN_PREFIX.length());
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWTUtil.SECRET)).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    String username = decodedJWT.getSubject();
//                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
//                    Collection<GrantedAuthority> authorities = new ArrayList<>();
//                    for (String role : roles) {
//                        authorities.add(new SimpleGrantedAuthority(role));
//                    }
                    List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
                    Collection<GrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } catch (TokenExpiredException e) {
                    response.setHeader("Error-Message", e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    throw e;
                }
                filterChain.doFilter(request, response);
            }
        }
    }
}