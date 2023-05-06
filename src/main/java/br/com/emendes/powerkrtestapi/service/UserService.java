package br.com.emendes.powerkrtestapi.service;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  UserResponse create(CreateUserRequest createUserRequest);

  Page<UserResponse> fetch(Pageable pageable);

  UserResponse findById(Long id);

  UserResponse update(Long id, UpdateUserRequest updateUserRequest);

  void delete(Long id);

}
