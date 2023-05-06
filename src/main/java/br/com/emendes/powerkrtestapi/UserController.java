package br.com.emendes.powerkrtestapi;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import br.com.emendes.powerkrtestapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponse> create(
      @RequestBody @Valid CreateUserRequest createUserRequest, UriComponentsBuilder uriComponentsBuilder) {
    UserResponse userResponse = userService.create(createUserRequest);
    URI uri = uriComponentsBuilder.path("/api/v1/users/{id}").build(userResponse.id());

    return ResponseEntity.created(uri).body(userResponse);
  }

  @GetMapping
  public ResponseEntity<Page<UserResponse>> fetch(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(userService.fetch(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> update(
      @PathVariable(name = "id") Long id,
      @RequestBody @Valid UpdateUserRequest updateUserRequest) {
    return ResponseEntity.ok(userService.update(id, updateUserRequest));
  }

}
