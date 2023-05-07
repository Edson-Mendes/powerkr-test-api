package br.com.emendes.powerkrtestapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Entidade JPA Role, refere-se a tabela t_role no banco de dados.<br>
 * Contém as roles que um usuário pode ter.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false, unique = true, length = 50)
  private String roleName;

  @Override
  public String getAuthority() {
    return this.roleName;
  }

}
