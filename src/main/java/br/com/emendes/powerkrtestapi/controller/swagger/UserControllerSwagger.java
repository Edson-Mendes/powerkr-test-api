package br.com.emendes.powerkrtestapi.controller.swagger;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static br.com.emendes.powerkrtestapi.util.constant.ApplicationConstants.SECURITY_SCHEME_KEY;

/**
 * Interface para manter as anotações referentes ao Springdoc,
 * com o objetivo de não "poluir" o UserController.
 */
@Tag(name = "Usuário", description = "API de gerenciamento de Usuário")
public interface UserControllerSwagger {

  @Operation(
      summary = "Criar usuário",
      description = "Criar usuário, não é necessário enviar o bearer token. Deve-se enviar as informações name, email " +
          "e password em um JSON no corpo da requisição.",
      tags = {"Usuário"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, como campos inválidos",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  ResponseEntity<UserResponse> create(CreateUserRequest createUserRequest, UriComponentsBuilder uriComponentsBuilder);

  @Operation(
      summary = "Buscar todos os usuários",
      description = "Buscar todos as usuários, é necessário enviar o bearer token no header Authorization.",
      tags = {"Usuário"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Usuários buscados com sucesso"),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content)
  })
  ResponseEntity<List<UserResponse>> fetchAll();

  @Operation(
      summary = "Buscar usuário por id",
      description = "Busca um usuário por id, é necessário enviar o bearer token no header Authorization. O id deve ser enviado no path da " +
          "requisição, exemplo: /api/v1/users/5",
      tags = {"Usuário"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Usuário encontrada com sucesso",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, como id inválido",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content),
      @ApiResponse(responseCode = "404", description = "Usuário não encontrada",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  ResponseEntity<UserResponse> findById(Long id);

  @Operation(
      summary = "Atualizar usuário",
      description = "Atualizar usuário, é necessário enviar o bearer token no header Authorization. Deve-se enviar as " +
          "informações name, e email em um JSON no corpo da requisição. O id deve ser enviado no path da requisição, " +
          "exemplo: /api/v1/users/8",
      tags = {"Usuário"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, como campos inválidos",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content),
      @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  ResponseEntity<UserResponse> update(Long id, UpdateUserRequest updateUserRequest);

  @Operation(
      summary = "Deletar usuário",
      description = "Deletar usuário, é necessário enviar o bearer token no header Authorization. Deve-se enviar o id no path da requisição, " +
          "exemplo: /api/v1/tasks/8",
      tags = {"Usuário"},
      security = {@SecurityRequirement(name = SECURITY_SCHEME_KEY)}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso",
          content = @Content),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, como id inválido",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content),
      @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  ResponseEntity<Void> delete(Long id);

}
