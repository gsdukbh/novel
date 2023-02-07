package top.werls.novel.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;


/**
 *
 *
 * @author Li JiaWei
 * @date 2023/2/6
 * @since on
 * @version TODO
 */
@Entity
@Schema(description = "用户实体类")
@Getter
@Setter
@ToString
public class SysUser extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

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

    @Exclude
    @ManyToMany(mappedBy = "sysUsers")
    private Set<SysRole> sysRoles = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        SysUser sysUser = (SysUser) o;
        return uid != null && Objects.equals(uid, sysUser.uid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
