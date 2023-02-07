package top.werls.novel.system.service.impl;

import jakarta.annotation.Resource;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import top.werls.novel.common.entity.SysUser;
import top.werls.novel.system.repository.SysRoleRepository;
import top.werls.novel.system.repository.SysUserRepository;


@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource
  private SysUserRepository sysUserRepository;
  @Resource
  private SysRoleRepository roleRepository;


  /**
   * Locates the user based on the username. In the actual implementation, the search may possibly
   * be case sensitive, or case insensitive depending on how the implementation instance is
   * configured. In this case, the <code>UserDetails</code> object that comes back may have a
   * username that is of a different case than what was actually requested..
   *
   * @param username the username identifying the user whose data is required.
   * @return a fully populated user record (never <code>null</code>)
   * @throws UsernameNotFoundException if the user could not be found or the user has no
   *                                   GrantedAuthority
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<SysUser> sysUser = sysUserRepository.findByUsername(username);
    if (sysUser.isEmpty()) {
      throw new UsernameNotFoundException("没有该用户！");
    }
    var u = sysUser.get();
    var roles = roleRepository.findBySysUsersUid(u.getUid());

    List<GrantedAuthority> authorities = new ArrayList<>();
    roles.forEach(i -> {
      authorities.add(i::getCode);
    });
    User user = new User(username, u.getPassword(),
        u.isEnabled(), u.isAccountNonExpired(), u.isCredentialsNonExpired(), u.isAccountNonLocked(),
        authorities);
    return user;
  }


}
