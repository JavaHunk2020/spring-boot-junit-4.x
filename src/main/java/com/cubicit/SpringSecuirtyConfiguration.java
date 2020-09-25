package com.cubicit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cubicit.service.UserSpringSecuirtyAuthProvider;

@Configuration 
@EnableWebSecurity 
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecuirtyConfiguration extends WebSecurityConfigurerAdapter { 
	
	
   @Autowired	
   private UserSpringSecuirtyAuthProvider userSpringSecurityAuthProvider;
   
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSpringSecurityAuthProvider).passwordEncoder(passwordEncoder());
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder(12);
   }
 
  @Override 
  protected void configure(HttpSecurity http) throws Exception {   
    
	http.authorizeRequests()//
      .antMatchers("/oauth").permitAll().
      antMatchers("/obiz").permitAll().
      // Disallow everything else..
      anyRequest().authenticated();
      
    http.csrf().disable();
    //for login
    http.formLogin().failureUrl("/oauth?error=true")
    .loginPage("/oauth")
    .defaultSuccessUrl("/customer/dashboard")
    /*.failureUrl("/corp/auth?error=true")*/
    .and().exceptionHandling()
    .accessDeniedPage("/access/denied")
    .and()
    .logout().logoutUrl("/customer/logout")
    .logoutSuccessUrl("/logout/success")
    .invalidateHttpSession(true) 
    .deleteCookies("JSESSIONID");
  } 
  
 
  /*@Bean
  public AuthenticationFailureHandler customAuthenticationFailureHandler() {
      return new LoginFailureUserAuthHandler(springSecurityService);
  }*/
  
}