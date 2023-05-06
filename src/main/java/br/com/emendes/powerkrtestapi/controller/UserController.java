package br.com.emendes.powerkrtestapi.controller;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import br.com.emendes.powerkrtestapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Classe que recebe as requisições que tratam do recurso User.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  /**
   * Método que trata a requisição POST /api/v1/users
   */
  @PostMapping
  public ResponseEntity<UserResponse> create(
      @RequestBody @Valid CreateUserRequest createUserRequest, UriComponentsBuilder uriComponentsBuilder) {
    UserResponse userResponse = userService.create(createUserRequest);
    URI uri = uriComponentsBuilder.path("/api/v1/users/{id}").build(userResponse.id());

    return ResponseEntity.created(uri).body(userResponse);
  }

  /**
   * Método que trata a requisição GET /api/v1/users
   */
  @GetMapping
  public ResponseEntity<List<UserResponse>> fetch(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(userService.fetchAll());
  }

  /**
   * Método que trata a requisição GET /api/v1/users/{id}, onde {id} é o identificador do recurso.
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  /**
   * Método que trata a requisição PUT /api/v1/users/{id}, onde {id} é o identificador do recurso.
   */
  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> update(
      @PathVariable(name = "id") Long id,
      @RequestBody @Valid UpdateUserRequest updateUserRequest) {
    return ResponseEntity.ok(userService.update(id, updateUserRequest));
  }

  /**
   * Método que trata a requisição DELETE /api/v1/users/{id}, onde {id} é o identificador do recurso.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
