package top.werls.novel.systemapi.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import top.werls.novel.systemapi.entity.SysUser;

/**
 * @author leejiawei
 */
@Data
public class LoginVo implements Serializable {

  @Serial
  private static final long serialVersionUID = 12L;
  private Long uid;

  @Schema(description = "用户名", example = "admin")
  private String username;
  String token;
}
