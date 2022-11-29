package top.werls.novel.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;


import java.io.Serial;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@Schema(description = "用户实体类")
public class SysUser extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "密码", example = "123456", required = true)
    private String password;
    @Schema
    private String salt;


    @Schema(description = "电话", example = "1231", required = true)
    private String phone;
    @Email
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "昵称", example = "admin")
    private String nickname;
    @Schema(description ="是否启用")
    private boolean enabled;
    @Schema(description ="账户未过期")
    private boolean accountNonExpired;
    @Schema(description ="凭据未过期")
    private boolean credentialsNonExpired;
    @Schema(description ="账户未锁定")
    private boolean accountNonLocked;

}
