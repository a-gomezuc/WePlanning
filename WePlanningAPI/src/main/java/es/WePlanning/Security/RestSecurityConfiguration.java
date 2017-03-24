package es.WePlanning.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/api/**");
		
		http.csrf().disable();
		
	    http.httpBasic();
	    
        
        // URLs that need authentication to access to it
        
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/plans/addPlan").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/viewFriendsPlans").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/plans/{id}/assist").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/plans/{id}/comment").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/user/**").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/plans/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/comments/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/plans/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin/plans/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin/users/{id}").hasAnyRole("ADMIN");
        

	    http.authorizeRequests().anyRequest().permitAll();
        
        http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}
	
}
