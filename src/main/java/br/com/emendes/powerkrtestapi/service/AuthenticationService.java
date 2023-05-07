package br.com.emendes.powerkrtestapi.service;

import br.com.emendes.powerkrtestapi.dto.request.AuthenticationRequest;
import br.com.emendes.powerkrtestapi.dto.response.AuthenticationResponse;

/**
 * Interface Service que contém as abstrações para Login de usuário.
 */
public interface AuthenticationService {

  /**
   * Autentica um usuário usando as credenciais recebidas em authenticationRequest.
   * @param authenticationRequest credenciais do usuário (email e password)
   * @return AuthenticationResponse contendo token (JWT a ser enviado em requisições que requerem autenticação),
   * type (tipo do token, nessa aplicação apenas Bearer)
   */
  AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
