package top.werls.novel.config.config;

import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.werls.novel.config.Security.CustomizeAccessDeniedHandler;
import top.werls.novel.config.Security.CustomizeAuthEntryPoint;
import top.werls.novel.config.Security.JwtAuthenticationTokenFilter;

@Configuration
public class SecurityConfig {

  @Resource
  private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

  @Resource
  private CustomizeAuthEntryPoint authEntryPoint;
  @Resource
  private CustomizeAccessDeniedHandler accessDeniedHandler;

  @Value("${env.isEnableSwagger}")
  private boolean isEnableSwagger;

  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
  }


  @Bean
  public AuthenticationManager CustomAuthenticationManager(AuthenticationConfiguration auth)
      throws Exception {
    return auth.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    if (isEnableSwagger) {
      http.authorizeHttpRequests((requests) ->
          requests.requestMatchers("/swagger-ui.html", "/webjars/**", "/swagger-ui*/**", "/v3/**")
              .permitAll());
    }
    http.cors(withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            (requests) ->
                requests.requestMatchers("/login", "/web/**", "/**.js", "/images/**.ico", "/**.css",
                        "/images/**.png", "/web").permitAll().anyRequest()
                    .authenticated()
        )
        .exceptionHandling((authorizeRequests) -> authorizeRequests
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authEntryPoint)
        )
        .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement((sessionManagement) -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
    return http.build();
  }
}
