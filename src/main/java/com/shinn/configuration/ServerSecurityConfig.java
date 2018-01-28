package com.shinn.configuration;

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

public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {

  public static final String REMEMBER_ME_KEY = "rememberme_key";
  
//  @Autowired
//  private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
  
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
