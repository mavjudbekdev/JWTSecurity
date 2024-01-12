package com.example.book.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JWTService {

    @Value("${spring.security.token.secret}")
    private String secret;

    @Value("${spring.security.token.expiration}")
    private Integer expirationHours;


    public String generateToken(UserDetails userDetails){
        Instant now = Instant.now();
        Instant expire = now.plus(expirationHours, ChronoUnit.HOURS);

        return Jwts.builder()
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expire))
                .setSubject(userDetails.getUsername())
                .signWith(signKey())
                .compact();
    }

    private Key signKey(){
        byte[] decode = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(decode);
    }


    public String getUsername(Claims claims){
        return claims.getSubject();
    }

    public boolean isExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return new Date().after(expiration);
    }


    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJwt(token).getBody();
    }

}
