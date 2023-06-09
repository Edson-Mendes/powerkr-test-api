package br.com.emendes.powerkrtestapi.service.impl;

import br.com.emendes.powerkrtestapi.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * Implementação de JwtService.
 */
@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

  @Value("${powerkr-test-api.jwt.expiration}")
  private String expiration;

  @Value("${powerkr-test-api.jwt.secret}")
  private String secret;

  @Override
  public String generateJWT(UserDetails userDetails) {
    return Jwts.builder()
        .setIssuer("PowerKR Test API")
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public boolean isTokenValid(String token) {
    try {
      return extractAllClaims(token).getExpiration().before(new Date());
    } catch (JwtException exception) {
      return false;
    }
  }

  @Override
  public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
  }

  private Key getKey() {
    byte[] secretBytes = secret.getBytes();
    return Keys.hmacShaKeyFor(secretBytes);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

}
