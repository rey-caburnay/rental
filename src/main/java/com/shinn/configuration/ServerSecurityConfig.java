package com.shinn.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.shinn.security.RememberMeServices;
import com.shinn.security.RestAuthenticationFailureHandler;
import com.shinn.security.RestAuthenticationSuccessHandler;
import com.shinn.security.RestUnauthorizedEntryPoint;
import com.shinn.service.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({DataSourceConfig.class})
@ComponentScan(basePackages = {"com.shinn.security"})
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
  private static final Logger logger = LoggerFactory.getLogger(ServerSecurityConfig.class);

  public static final String REMEMBER_ME_KEY = "rememberme_key";

  public ServerSecurityConfig() {
    super();
    logger.info("loading SecurityConfig ................................................ ");
  }


  @Autowired
  DataSourceConfig dataSourceConfig;
  
  @Autowired
  private UserDetailsService userDetailsService;

  //
  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Autowired
  private RestUnauthorizedEntryPoint restUnauthorizedEntryPoint;

  @Autowired
  private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

  @Autowired
  private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

  @Autowired
  private RememberMeServices rememberMeServices;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    auth.authenticationProvider(authProvider);
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
      tokenRepositoryImpl.setDataSource(dataSourceConfig.dataSource());
      return tokenRepositoryImpl;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**", "/WEB-INF/views/**", "/index.jsp", "/index.html",
        "/login", "/");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {


    http.headers().disable().csrf().disable().authorizeRequests().antMatchers("/").permitAll()
        .antMatchers("/bill/**").hasAnyAuthority("admin").antMatchers("/collection/**")
        .hasAnyAuthority("admin").antMatchers("/user/**").hasAnyAuthority("admin")
        .antMatchers("/mst/**").hasAnyAuthority("admin").antMatchers("/notify/**")
        .hasAnyAuthority("admin").antMatchers("/report/**").hasAnyAuthority("admin")
        .antMatchers("/tx/**").hasAnyAuthority("admin").antMatchers("/sms/**").authenticated()
        .antMatchers("/mst/**").authenticated().antMatchers("/mst/**").authenticated().anyRequest()
        .authenticated().and().exceptionHandling()
        .authenticationEntryPoint(restUnauthorizedEntryPoint)
        .accessDeniedHandler(accessDeniedHandler).and().formLogin()
        .loginProcessingUrl("/authenticate").successHandler(restAuthenticationSuccessHandler)
        .failureHandler(restAuthenticationFailureHandler).usernameParameter("username")
        .passwordParameter("password").permitAll().and().logout().logoutUrl("/logout")
        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
        .deleteCookies("JSESSIONID").permitAll().and().rememberMe().rememberMeParameter("remember-me")
        .tokenRepository(persistentTokenRepository()).tokenValiditySeconds(RememberMeServices.TWO_WEEKS_S).and();
  }

}
