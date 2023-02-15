package top.werls.novel.systemapi.vo;


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
    SysUser user;
    String token;
}
