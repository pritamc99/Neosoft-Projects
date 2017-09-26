package mypacks.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource dataSource;
	
	
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        /*auth
	            .inMemoryAuthentication()
	                .withUser("admin").password("nimda").roles("ADMIN");*/
		 	auth
		 	.jdbcAuthentication()
		 	.dataSource(dataSource)
		 	.passwordEncoder(new BCryptPasswordEncoder())
		 	.usersByUsernameQuery("select UserName,Password,enabled from Register where username = ?")
		 	.authoritiesByUsernameQuery("select UserName,UserRoleName from Register where UserName=?");
	    }
	     
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	   
	      http.authorizeRequests()
	        //.antMatchers("/").permitAll()
	        .antMatchers("/admin**").access("hasRole('ROLE_ADMIN')")
	        
	        .and().formLogin()
	       /* .defaultSuccessUrl("/admin")*/
	        .failureUrl("/index1")
	        .and()
	        .exceptionHandling().accessDeniedPage("/index1")
	        ;
	       
	      http
	      .logout()
	      .logoutUrl("/AdminLogout")
	      .logoutSuccessUrl("/Login")
	      .invalidateHttpSession(true);
	      
	      http.csrf().disable();
	    }    
}
