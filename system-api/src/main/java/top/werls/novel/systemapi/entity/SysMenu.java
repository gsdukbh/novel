package top.werls.novel.systemapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import top.werls.novel.common.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "sys_menu")
public class SysMenu extends BaseEntity implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull
  @Column(name = "name")
  @Schema(title = "名称")
  private String name;

  @NotNull
  @Column(name = "menu_code", unique = true)
  @Schema(title = "code")
  private String menuCode;

  @Column(name = "parent_id", columnDefinition = "父节点")
  @Schema(title = "父节点")
  private Integer parentId;

  @NotNull
  @Column(name = "path")
  private String path;

  @NotNull
  @Column(name = "component")
  private String component;

}