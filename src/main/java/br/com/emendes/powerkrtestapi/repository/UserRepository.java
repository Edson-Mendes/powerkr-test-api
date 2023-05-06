package br.com.emendes.powerkrtestapi.repository;

import br.com.emendes.powerkrtestapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
