package br.com.emendes.powerkrtestapi.mapper;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import br.com.emendes.powerkrtestapi.model.entity.User;

public interface UserMapper {

  User createUserRequestToUser(CreateUserRequest createUserRequest);

  UserResponse userToUserResponse(User user);

}
