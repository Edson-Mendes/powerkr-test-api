package br.com.emendes.powerkrtestapi.service.impl;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
import br.com.emendes.powerkrtestapi.dto.response.UserResponse;
import br.com.emendes.powerkrtestapi.exception.EmailAlreadyInUseException;
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
   * Método responsável por persistir um User no banco dados.
   * @param createUserRequest que contém os dados do User que será salvo.
   * @return UserResponse contendo id, name e email do usuário.
   * @throws EmailAlreadyInUseException caso o email informado já esteja salvo na base de dados.
   */
  @Override
  public UserResponse create(CreateUserRequest createUserRequest) {
    User user = userMapper.createUserRequestToUser(createUserRequest);
    user.setPassword(passwordEncoder.encode(createUserRequest.password()));

    try {
      userRepository.save(user);
      log.info("user {} successfully created with id : {}", user.getEmail(), user.getId());
    } catch (DataIntegrityViolationException exception) {
      throw new EmailAlreadyInUseException(String.format("Email {%s} already in use", user.getEmail()));
    }

    return userMapper.userToUserResponse(user);
  }

  /**
   *  Método responsável por buscar Users na base de dados, busca paginada.
   * @param pageable que será usado para busca paginada, por padrão buscará a page 0 com size 10.
   * @return Page de UserResponse
   */
  @Override
  public Page<UserResponse> fetch(Pageable pageable) {
    Page<User> userPage = userRepository.findAll(pageable);
    log.info("Fetching page {}, size {} of User", pageable.getPageNumber(), pageable.getPageSize());

    return userPage.map(userMapper::userToUserResponse);
  }

}
