package com.library.springbootlibrary;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringBootLibrarySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    DataSource dataSource;

	//Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder());
    }

    /*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()().passwordEncoder(passwordEncoder());
	}
    
    @Resource(name = "userDetailService")
	private UserDetailsService userDetailsService;*/
	
    @Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/subject/*").hasRole("PRIN")
				.antMatchers("/book/*").hasRole("LIBRARIAN").antMatchers("/user/*")
				.permitAll().antMatchers("/index.html").hasAnyRole("PRIN","LIBRARIAN");
				//.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				//.and().logout().permitAll();

		http.csrf().disable();
	}

    //remove this in memory authentication configuration
	// @Autowired
	//public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
	//	authenticationMgr.inMemoryAuthentication().withUser("admin").password("admin").authorities("USER").and()
	//			.withUser("javainuse").password("javainuse").authorities("USER", "ADMIN");
	//} 

}