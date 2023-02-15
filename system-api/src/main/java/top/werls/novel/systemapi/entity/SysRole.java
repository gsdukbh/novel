package top.werls.novel.systemapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import top.werls.novel.common.entity.BaseEntity;

/**
 *
 *
 * @author Li JiaWei
 * @date 2023/2/6
 * @since on
 * @version TODO
 */
@Entity
@Table(name = "sys_role")
@Setter
@Getter
@ToString
public class SysRole extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 202338140201L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "code", unique = true)
  private String code;

  @Exclude
  @ManyToMany
  @JoinTable(name = "sys_role_users",
      joinColumns = @JoinColumn(name = "sys_role_id"),
      inverseJoinColumns = @JoinColumn(name = "sys_users_uid"))
  private Set<SysUser> sysUsers = new LinkedHashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SysRole sysRole)) {
      return false;
    }
    return Objects.equals(id, sysRole.id) && Objects.equals(name, sysRole.name)
        && Objects.equals(code, sysRole.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, code);
  }
}