package com.knigaliz.profile.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {
    private final String secret = "secretksecretisecretm";

    public String generateToken(String login) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(30).toInstant());

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

        DecodedJWT jwt = JWT.decode(token);
        expirationCheck(jwt);
        verifier.verify(jwt);

        return jwt.getClaim("login").asString();
    }

    public void expirationCheck(DecodedJWT jwt) {
        if (jwt.getExpiresAt().before(new Date())) throw new JWTVerificationException("token already expired");
    }
}
