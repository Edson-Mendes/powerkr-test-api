package br.com.emendes.powerkrtestapi.repository;

import br.com.emendes.powerkrtestapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<UserDetails> findByEmail(String username);

}
