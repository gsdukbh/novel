package top.werls.novel.config.init;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.werls.novel.common.entity.SysUser;
import top.werls.novel.system.repository.SysRoleRepository;
import top.werls.novel.system.repository.SysUserRepository;

/**
 * @author Li JiaWei
 * @version TODO
 * @date 2023/2/5
 * @since on
 */
@Component
@Slf4j
public class InitApp {

  @Value("{init.username}")
  private String username;

  @Value("{init.password}")
  private String password;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private SysRoleRepository sysRoleRepository;
  @Resource
  private SysUserRepository userRepository;

  public void init() {
    var hasUser = userRepository.findByUsername(username);
    if (hasUser.isEmpty()) {
      log.warn("""
           ___  ________   ___  _________  \s
          |\\  \\|\\   ___  \\|\\  \\|\\___   ___\\\s
          \\ \\  \\ \\  \\\\ \\  \\ \\  \\|___ \\  \\_|\s
           \\ \\  \\ \\  \\\\ \\  \\ \\  \\   \\ \\  \\ \s
            \\ \\  \\ \\  \\\\ \\  \\ \\  \\   \\ \\  \\\s
             \\ \\__\\ \\__\\\\ \\__\\ \\__\\   \\ \\__\\
              \\|__|\\|__| \\|__|\\|__|    \\|__|
          第一次启动初始化配置
          """);
      SysUser user = new SysUser();
      user.setUsername(username);
      user.setPassword(passwordEncoder.encode(password));
      user.setEnabled(true);
      user.setAccountNonExpired(true);
      user.setAccountNonLocked(true);
      user.setCredentialsNonExpired(true);
    }

  }

  public void initUser() {

  }
}
