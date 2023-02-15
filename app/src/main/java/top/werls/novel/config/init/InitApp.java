package top.werls.novel.config.init;


import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.werls.novel.system.repository.SysRoleRepository;
import top.werls.novel.system.repository.SysUserRepository;
import top.werls.novel.systemapi.entity.SysRole;
import top.werls.novel.systemapi.entity.SysUser;

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

  public void initRole() {
    SysRole role = new SysRole();
    role.setCode("ROLE_ADMIN");
    role.setName("管理员");
    List<SysRole> roleList = new ArrayList<>();
    roleList.add(role);
    role.setName("用户");
    role.setCode("ROLE_USER");
    roleList.add(role);
    sysRoleRepository.saveAll(roleList);
  }
}
