package br.com.emendes.powerkrtestapi.mapper;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import br.com.emendes.powerkrtestapi.model.entity.User;

/**
 * Interface component que contém as abstrações de mapeamento de DTOs para a entidade User e vice-versa.
 */
public interface UserMapper {

  /**
   * Mapeia o DTO CreateUserRequest para a entidade User.
   * @param createUserRequest que deve ser mapeado para User
   * @return User com dados vindo de createUserRequest.
   */
  User createUserRequestToUser(CreateUserRequest createUserRequest);

  /**
   * Mapeia uma entidade User para o DTO UserResponse.
   * @param user que deve ser mapeado para UserResponse
   * @return UserResponse com as informações necessárias de User.
   */
  UserResponse userToUserResponse(User user);

  /**
   * Insere os dados vindo do DTO UpdateUserRequest em um objeto User
   * @param updateUserRequest que contém os novos dados de User.
   * @param user que receberá os novos dados vindo de updateUserRequest
   */
  void merge(UpdateUserRequest updateUserRequest, User user);

}
