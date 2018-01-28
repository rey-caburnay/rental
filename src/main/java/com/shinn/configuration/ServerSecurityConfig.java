package com.shinn.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;

import com.shinn.security.UserDetailsService;

public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
  private static final Logger logger = LoggerFactory.getLogger(ServerSecurityConfig.class);

  public static final String REMEMBER_ME_KEY = "rememberme_key";

  public ServerSecurityConfig() {
      super();
      logger.info("loading SecurityConfig ................................................ ");
  }
  
  
  @Autowired
  private UserDetailsService userDetailService;
  
  @Autowired
  private AccessDeniedHandler restAccessDeniedHandler;

  @Autowired
  private AuthenticationSuccessHandler restAuthenticationSuccessHandler;

  @Autowired
  private AuthenticationFailureHandler restAuthenticationFailureHandler;

  @Autowired
  private RememberMeServices rememberMeServices;

  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("john").password("123").roles("USER");
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and()
        .formLogin().permitAll();
  }


}
