package com.productsystem.security;

import com.productsystem.entity.Role;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.nio.charset.StandardCharsets;

import java.sql.Time;
import java.util.Date;

@Component
public class JWTUtil {
    private final Key SECRET_KEY =
            Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey12".getBytes(StandardCharsets.UTF_8));
    public String generateToken(String email, Role role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public String extractRole(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("role",String.class);
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported token");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid token");
        } catch (SignatureException e) {
            System.out.println("Invalid signature");
        } catch (IllegalArgumentException e) {
            System.out.println("Token is empty");
        }

        return false;
    }
}
