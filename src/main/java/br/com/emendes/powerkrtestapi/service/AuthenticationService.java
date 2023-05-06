package br.com.emendes.powerkrtestapi.service;

import br.com.emendes.powerkrtestapi.dto.request.AuthenticationRequest;
import br.com.emendes.powerkrtestapi.dto.response.AuthenticationResponse;

/**
 * Interface Service que contém as abstrações para Login de usuário.
 */
public interface AuthenticationService {

  AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
