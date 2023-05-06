package br.com.emendes.powerkrtestapi.mapper.impl;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import br.com.emendes.powerkrtestapi.mapper.UserMapper;
import br.com.emendes.powerkrtestapi.model.entity.User;
import org.springframework.stereotype.Component;

/**
 * Implementação de UserMapper.
 */
@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public User createUserRequestToUser(CreateUserRequest createUserRequest) {
    return User.builder()
        .name(createUserRequest.name())
        .email(createUserRequest.email())
        .build();
  }

  @Override
  public UserResponse userToUserResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }

  @Override
  public void merge(UpdateUserRequest updateUserRequest, User user) {
    user.setName(updateUserRequest.name());
    user.setEmail(updateUserRequest.email());
  }

}
