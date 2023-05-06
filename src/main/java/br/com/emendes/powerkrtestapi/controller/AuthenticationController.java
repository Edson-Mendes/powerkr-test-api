package br.com.emendes.powerkrtestapi.controller;

import br.com.emendes.powerkrtestapi.dto.request.AuthenticationRequest;
import br.com.emendes.powerkrtestapi.dto.response.AuthenticationResponse;
import br.com.emendes.powerkrtestapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe que recebe as requisições que tratam do login do usuário.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Método que trata a requisição POST /api/v1/tasks<br>
   * Não é necessário estar autenticado.
   */
  @PostMapping
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody @Valid AuthenticationRequest authenticationRequest) {
    return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
  }

}
