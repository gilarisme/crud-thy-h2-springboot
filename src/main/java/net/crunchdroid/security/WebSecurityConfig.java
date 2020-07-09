package net.crunchdroid.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
	     .authorizeRequests()
	     .antMatchers("/static/**").permitAll()
	     .antMatchers("/webjars/**").permitAll()
	     .antMatchers("classpath:/META-INF/resources/webjars/").permitAll()
	     .anyRequest().authenticated()
	     .and()
         .formLogin().loginPage("/login")
         .loginProcessingUrl("/login")
         .failureUrl("/login?error=true")
         .defaultSuccessUrl("/plain-page").permitAll()
         .and()
         .logout().logoutUrl("/logout")
         .logoutSuccessUrl("/")
         .clearAuthentication(true)
         .deleteCookies("SESSIONID")
         .invalidateHttpSession(true);
	 }
}
