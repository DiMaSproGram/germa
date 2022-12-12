package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usertype")
public class UserType implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_type")
  public int typeId;

  @Column(name = "Typename")
  private String typeName;

  @Override
  public String getAuthority() {
    return getTypeName();
  }
}
