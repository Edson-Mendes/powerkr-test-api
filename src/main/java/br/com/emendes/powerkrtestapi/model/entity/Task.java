package br.com.emendes.powerkrtestapi.model.entity;

import br.com.emendes.powerkrtestapi.model.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidade JPA Tarefa, refere-se a tabela t_task no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "t_task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 150)
  private String title;
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private LocalDateTime creationDate;
  private LocalDateTime conclusionDate;
  @Column(nullable = false, length = 50)
  @Enumerated(EnumType.STRING)
  private TaskStatus status;

}
