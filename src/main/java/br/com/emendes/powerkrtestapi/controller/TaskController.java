package br.com.emendes.powerkrtestapi.controller;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;
import br.com.emendes.powerkrtestapi.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Classe que recebe as requisições que tratam do recurso Task.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private final TaskService taskService;

  /**
   * Método que trata a requisição POST /api/v1/tasks<br>
   * É necessário estar autenticado.
   */
  @PostMapping
  public ResponseEntity<TaskResponse> create(
      @RequestBody @Valid CreateTaskRequest createTaskRequest, UriComponentsBuilder uriComponentsBuilder) {
    TaskResponse taskResponse = taskService.create(createTaskRequest);

    URI uri = uriComponentsBuilder.path("/api/v1/tasks/{id}").build(taskResponse.id());

    return ResponseEntity.created(uri).body(taskResponse);
  }

  /**
   * Método que trata a requisição GET /api/v1/tasks<br>
   * É necessário estar autenticado.
   */
  @GetMapping
  public ResponseEntity<List<TaskResponse>> fetchAll() {
    return ResponseEntity.ok(taskService.fetchAll());
  }

  /**
   * Método que trata a requisição GET /api/v1/tasks/{id}, onde {id} é o identificador do recurso.<br>
   * É necessário estar autenticado.
   */
  @GetMapping("/{id}")
  public ResponseEntity<TaskResponse> findById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(taskService.findById(id));
  }

  /**
   * Método que trata a requisição PUT /api/v1/tasks/{id}, onde {id} é o identificador do recurso.<br>
   * É necessário estar autenticado.
   */
  @PutMapping("/{id}")
  public ResponseEntity<TaskResponse> update(
      @PathVariable(name = "id") Long id,
      @RequestBody @Valid UpdateTaskRequest updateTaskRequest) {
    return ResponseEntity.ok(taskService.update(id, updateTaskRequest));
  }

  /**
   * Método que trata a requisição DELETE /api/v1/tasks/{id}, onde {id} é o identificador do recurso.<br>
   * É necessário estar autenticado.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
    taskService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
