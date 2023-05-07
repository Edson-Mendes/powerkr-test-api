package br.com.emendes.powerkrtestapi.service.impl;

import br.com.emendes.powerkrtestapi.dto.request.AuthenticationRequest;
import br.com.emendes.powerkrtestapi.dto.response.AuthenticationResponse;
import br.com.emendes.powerkrtestapi.service.AuthenticationService;
import br.com.emendes.powerkrtestapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Implementação de AuthenticationService.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String token = jwtService.generateJWT(userDetails);
    log.info("JWT generated successfully for user {}", userDetails.getUsername());

    return AuthenticationResponse.builder()
        .token(token)
        .type("Bearer")
        .build();
  }

}
