package top.werls.novel.system.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.werls.novel.common.utils.JwtTokenUtils;
import top.werls.novel.systemapi.param.LoginParam;
import top.werls.novel.system.service.SysUserService;
import top.werls.novel.systemapi.repository.SysUserRepository;
import top.werls.novel.systemapi.vo.LoginVo;


@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

  @Resource
  private UserDetailsServiceImpl userDetailsService;

  @Resource
  private PasswordEncoder passwordEncoder;
  @Resource
  private JwtTokenUtils tokenUtils;
  @Resource
  private SysUserRepository sysUserRepository;

  /**
   * 登录
   *
   * @param param
   * @return
   */
  @Override
  public LoginVo login(LoginParam param) {

    UserDetails userDetails = userDetailsService.loadUserByUsername(param.getUsername());

    if (!passwordEncoder.matches(param.getPassword(), userDetails.getPassword())) {
      throw new BadCredentialsException("密码错误");
    }
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    LoginVo loginVo = new LoginVo();
    loginVo.setToken(tokenUtils.generateToken(userDetails.getUsername()));
    var user = sysUserRepository.findByUsername(param.getUsername()).orElse(null);
    loginVo.setUser(user);
    return loginVo;
  }
}
