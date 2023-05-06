package br.com.emendes.powerkrtestapi.service.impl;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import br.com.emendes.powerkrtestapi.exception.EmailAlreadyInUseException;
import br.com.emendes.powerkrtestapi.exception.ResourceNotFoundException;
import br.com.emendes.powerkrtestapi.mapper.UserMapper;
import br.com.emendes.powerkrtestapi.model.entity.User;
import br.com.emendes.powerkrtestapi.repository.UserRepository;
import br.com.emendes.powerkrtestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service responsável pelas regras de negócio envolvendo o recurso User.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  /**
   * Método responsável por persistir um Usuário no banco dados.
   *
   * @param createUserRequest contém os dados do Usuário que será salvo.
   * @return UserResponse contendo id, name e email do usuário.
   */
  @Override
  public UserResponse create(CreateUserRequest createUserRequest) {
    User user = userMapper.createUserRequestToUser(createUserRequest);
    user.setPassword(passwordEncoder.encode(createUserRequest.password()));

    persistUser(user);

    return userMapper.userToUserResponse(user);
  }

  /**
   * Método responsável por buscar Usuários na base de dados, busca paginada.
   *
   * @param pageable que será usado para busca paginada, por padrão buscará a page 0 com size 10.
   * @return Page de UserResponse
   */
  @Override
  public Page<UserResponse> fetch(Pageable pageable) {
    Page<User> userPage = userRepository.findAll(pageable);
    log.info("Fetching page {}, size {} of User", pageable.getPageNumber(), pageable.getPageSize());

    return userPage.map(userMapper::userToUserResponse);
  }

  /**
   * Método responsável por buscar um Usuário na base de dados.
   *
   * @param id identificador do usuário a ser buscado.
   * @return UserResponse contendo id, name e email do usuário.
   */
  @Override
  public UserResponse findById(Long id) {
    return userMapper.userToUserResponse(findUserById(id));
  }

  /**
   * Método responsável por atualizar um Usuário.
   * @param id identificador do usuário a ser atualizado.
   * @param updateUserRequest contém os novos dados do Usuário.
   * @return UserResponse contendo id, name e email do usuário.
   */
  @Override
  public UserResponse update(Long id, UpdateUserRequest updateUserRequest) {
    log.info("Attempt update user with id : {}", id);
    User user = findUserById(id);
    userMapper.merge(updateUserRequest, user);

    persistUser(user);

    return userMapper.userToUserResponse(user);
  }

  /**
   * Método responsável por deletar um usuário do banco de dados.
   * @param id identificador do usuário a ser deletado.
   */
  @Override
  public void delete(Long id) {
    log.info("Attempt delete user with id : {}", id);
    User user = findUserById(id);

    userRepository.delete(user);
    log.info("user with id : {} successfully deleted from database", id);
  }

  /**
   * Método privado para buscar um usuário no banco de dados.
   * @param id identificador do usuário a ser atualizado.
   * @return User encontrado no banco de dados.
   * @throws ResourceNotFoundException caso não exista um usuário com o id informado.
   */
  private User findUserById(Long id) {
    log.info("Searching for user with id : {}", id);

    return userRepository.findById(id)
        .orElseThrow(() -> {
          log.info("User not found with id : {}", id);
          return new ResourceNotFoundException("User not found with id " + id);
        });
  }

  /**
   * Método privado para persistir um Usuário no banco de dados.
   * @param user usuário que será adicionado/atualizado ao banco de dados.
   * @throws EmailAlreadyInUseException caso o email informado já esteja salvo na base de dados.
   */
  private void persistUser(User user) {
    try {
      userRepository.save(user);
      log.info("user {} successfully persisted with id : {}", user.getEmail(), user.getId());
    } catch (DataIntegrityViolationException exception) {
      log.info("Data conflict, the email {} is already in database", user.getEmail());
      throw new EmailAlreadyInUseException(String.format("Email {%s} already in use", user.getEmail()));
    }
  }

}
