package top.werls.novel.config.init;


import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.werls.novel.systemapi.entity.Dict;
import top.werls.novel.systemapi.repository.DictRepository;
import top.werls.novel.systemapi.repository.SysRoleRepository;
import top.werls.novel.systemapi.repository.SysUserRepository;
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
public class InitApp implements ApplicationRunner {

  @Value("${init.username}")
  private String username;

  @Value("${init.password}")
  private String password;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private SysRoleRepository sysRoleRepository;
  @Resource
  private SysUserRepository userRepository;
  @Resource
  private DictRepository dictRepository;

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
      var d = dictRepository.findByCode("boostrap");
      if (d.isEmpty()) {
        initRole();
        initUser();
        var dict = new Dict();
        dict.setCode("boostrap");
        dict.setName("fist run");
        dictRepository.save(dict);
      }

    }

  }

  private void initUser() {
    SysUser user = new SysUser();
    user.setNickname("admin");
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setEnabled(true);
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setCredentialsNonExpired(true);
    userRepository.save(user);
    var role = sysRoleRepository.findByCode("ROLE_ADMIN");
    if (role.isPresent()) {
      var tem = role.get();
      var users= new HashSet<SysUser>();
      users.add(user);
      tem.setSysUsers(users);
      sysRoleRepository.save(tem);
    }
  }

  private void initRole() {
    SysRole role = new SysRole();
    role.setCode("ROLE_ADMIN");
    role.setName("管理员");
    sysRoleRepository.save(role);
    var user = new SysRole();
    user.setName("用户");
    user.setCode("ROLE_USER");
    sysRoleRepository.save(user);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    init();
  }
}
