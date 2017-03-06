package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.csrf().disable();
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/user/**").permitAll();
        http.authorizeRequests().antMatchers("/contact").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers("/plan/**").permitAll();
        http.authorizeRequests().antMatchers("/aboutus").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/logged").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/newPlan").hasAnyRole("USER");

        // Login form
        http.formLogin().loginPage("/");
        http.formLogin().usernameParameter("id");
        http.formLogin().passwordParameter("pass");
        http.formLogin().defaultSuccessUrl("/logged");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(authenticationProvider);
    }
}
