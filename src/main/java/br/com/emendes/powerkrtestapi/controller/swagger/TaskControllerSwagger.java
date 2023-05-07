package br.com.emendes.powerkrtestapi.controller.swagger;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;
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
 * com o objetivo de não "poluir" o TaskController.
 */
@Tag(name = "Tarefa", description = "API de gerenciamento de Tarefa")
@SecurityRequirement(name = SECURITY_SCHEME_KEY)
public interface TaskControllerSwagger {

  @Operation(
      summary = "Criar tarefa",
      description = "Criar tarefa, é necessário enviar o bearer token no header Authorization. Deve-se enviar as informações title, description " +
          "e creationDate em um JSON no corpo da requisição.",
      tags = {"Tarefa"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponse.class))),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, como campos inválidos",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content)
  })
  ResponseEntity<TaskResponse> create(CreateTaskRequest createTaskRequest, UriComponentsBuilder uriComponentsBuilder);

  @Operation(
      summary = "Buscar todas as tarefas",
      description = "Buscar todas as tarefas, é necessário enviar o bearer token no header Authorization.",
      tags = {"Tarefa"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Tarefas buscadas com sucesso"),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content)
  })
  ResponseEntity<List<TaskResponse>> fetchAll();

  @Operation(
      summary = "Buscar tarefa por id",
      description = "Busca uma tarefa por id, é necessário enviar o bearer token no header Authorization. O id deve ser enviado no path da " +
          "requisição, exemplo: /api/v1/tasks/8",
      tags = {"Tarefa"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponse.class))),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, como id inválido",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content),
      @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  ResponseEntity<TaskResponse> findById(Long id);

  @Operation(
      summary = "Atualizar tarefa",
      description = "Atualizar tarefa, é necessário enviar o bearer token no header Authorization. Deve-se enviar as informações title, description, " +
          "creationDate e status em um JSON no corpo da requisição. O id deve ser enviado no path da requisição, " +
          "exemplo: /api/v1/tasks/8",
      tags = {"Tarefa"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponse.class))),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, como campos inválidos",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content),
      @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  ResponseEntity<TaskResponse> update(Long id, UpdateTaskRequest updateTaskRequest);

  @Operation(
      summary = "Deletar tarefa",
      description = "Deletar tarefa, é necessário enviar o bearer token no header Authorization. Deve-se enviar o id no path da requisição, " +
          "exemplo: /api/v1/tasks/8",
      tags = {"Tarefa"}
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso",
          content = @Content),
      @ApiResponse(responseCode = "400", description = "Algo de errado com a requisição, como id inválido",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized, cliente não enviou token no header Authorization, " +
          "ou enviou um token inválido", content = @Content),
      @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  ResponseEntity<Void> delete(Long id);

}
