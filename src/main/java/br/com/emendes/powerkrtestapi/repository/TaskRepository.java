package br.com.emendes.powerkrtestapi.repository;

import br.com.emendes.powerkrtestapi.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
