package br.com.sistemagestaoacademica.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getChave() {
        byte[] chaveBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(chaveBytes);
    }

    public String gerarToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getChave())
                .compact();
    }

    public String extrairEmail(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    public String extrairRole(String token) {
        return extrairClaim(token, claims -> claims.get("role", String.class));
    }

    private Date extrairDataExpiracao(String token){
        return extrairClaim(token,Claims::getExpiration);
    }

    private <T> T extrairClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getChave())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }

    public boolean tokenValido(String token, String email) {
        String emailDoToken = extrairEmail(token);
        return emailDoToken.equals(email) && !tokenExpirado(token);
    }

    private boolean tokenExpirado(String token) {
        return extrairDataExpiracao(token).before(new Date());
    }
}
