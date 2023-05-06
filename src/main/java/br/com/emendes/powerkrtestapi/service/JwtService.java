package br.com.emendes.powerkrtestapi.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface Service que contém as abstrações para manipulação de JWT.
 */
public interface JwtService {

  /**
   * Gera um JWT a partir das informações de UserDetails.
   * @param userDetails userDetails do usuário.
   * @return JWT
   */
  String generateJWT(UserDetails userDetails);

}
