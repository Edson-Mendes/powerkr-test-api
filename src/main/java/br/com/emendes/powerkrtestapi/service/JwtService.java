package br.com.emendes.powerkrtestapi.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface Service que contém as abstrações para manipulação de JWT.
 */
public interface JwtService {

  /**
   * Gera um JWT a partir das informações de UserDetails.
   *
   * @param userDetails userDetails do usuário.
   * @return JWT
   */
  String generateJWT(UserDetails userDetails);

  /**
   * Verifica se o token informado é válido e se não está expirado.
   *
   * @param token a ser verificado.
   * @return true se o token for válido, false caso contrário.
   */
  boolean isTokenValid(String token);

  /**
   * Extrair o username/subject do JWT.
   *
   * @param token JWT enviado no header authorization.
   * @return email do usuário a quem pertece o JWT.
   */
  String extractUsername(String token);

}
