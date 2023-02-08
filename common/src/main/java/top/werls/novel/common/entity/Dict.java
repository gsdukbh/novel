package top.werls.novel.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 *
 * @author Li JiaWei
 * @date 2023/2/6
 * @since on
 * @version TODO
 */
@Entity
@Table(name = "dict")
@Getter
@Setter
public class Dict extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 202338140144L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "code", unique = true)
  private String code;

  @Column(name = "value")
  private String value;

}