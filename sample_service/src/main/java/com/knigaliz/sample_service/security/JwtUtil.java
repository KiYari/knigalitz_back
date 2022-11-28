package com.knigaliz.sample_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.knigaliz.sample_service.dto.Dto;
import com.knigaliz.sample_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {
    private static UserService userService;
    private static AuthenticationProvider authenticationProvider;
    private static String secret;

    @Value("${secret}")
    public void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

    @Autowired
    public JwtUtil(UserService userService, AuthenticationProvider authenticationProvider) {
        JwtUtil.userService = userService;
        JwtUtil.authenticationProvider = authenticationProvider;
    }

    public static String generateToken(String login) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(1).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("login", login)
                .withIssuedAt(new Date())
                .withIssuer("auth-server")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetriveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("auth-server")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        System.out.println("no expired chcek");

        return jwt.getClaim("login").asString();
    }

    public static String authenticateAndGetToken(Dto dto) {
        String username = dto.getLogin();
        UserDetails user = userService.loadUserByUsername(username);
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(username, dto.getPassword(), user.getAuthorities())
        );

        return generateToken(username);
    }
}
