package br.com.emendes.powerkrtestapi.controller.swagger;

import br.com.emendes.powerkrtestapi.dto.request.AuthenticationRequest;
import br.com.emendes.powerkrtestapi.dto.response.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

/**
 * Interface para manter as anotações referentes ao Springdoc,
 * com o objetivo de não "poluir" o AuthenticationController.
 */
@Tag(name = "Autenticação", description = "API de gerenciamento de autenticação")
public interface AuthenticationControllerSwagger {

  @Operation(
      summary = "Realizar login do Usuário e gerar token",
      description = "Realizar login do Usuário. Se as credenciais forem válidas, um token é enviado no corpo da resposta, " +
          "tal token deve ser enviado no header Authorization em todas as requisições que requerem usuário autenticado.",
      tags = {"Autenticação"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Credenciais válidas e token gerado com sucesso",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, credenciais inválidas ou campos inválidos",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);

}
