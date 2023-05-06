package br.com.emendes.powerkrtestapi.service;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;

import java.util.List;

/**
 * Interface Service que contém as abstrações que manipulam o recurso User.
 */
public interface UserService {

  /**
   * Persisti um Usuário no banco de dados.
   *
   * @param createUserRequest contém os dados do Usuário que será salvo.
   * @return UserResponse contendo id, name e email do usuário.
   */
  UserResponse create(CreateUserRequest createUserRequest);

  /**
   * Busca todos os Usuários no banco de dados.
   *
   * @return List de UserResponse
   */
  List<UserResponse> fetchAll();

  /**
   * Busca um Usuário no banco de dados.
   *
   * @param id identificador do usuário a ser buscado.
   * @return UserResponse contendo id, name e email do usuário.
   */
  UserResponse findById(Long id);

  /**
   * Método responsável por atualizar um Usuário.
   *
   * @param id                identificador do usuário a ser atualizado.
   * @param updateUserRequest contém os novos dados do Usuário.
   * @return UserResponse contendo id, name e email do usuário.
   */
  UserResponse update(Long id, UpdateUserRequest updateUserRequest);

  /**
   * Deletar um usuário do banco de dados.
   *
   * @param id identificador do usuário a ser deletado.
   */
  void delete(Long id);

}
