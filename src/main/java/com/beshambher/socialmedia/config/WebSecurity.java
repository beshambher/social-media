package com.beshambher.socialmedia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.beshambher.socialmedia.service.oauth.CustomOidcUserDetailsService;
import com.beshambher.socialmedia.service.oauth.CustomUserDetailsService;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Value("${server.auth.uri.redirect}")
	private String redirectUri;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private CustomOidcUserDetailsService customOidcUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		 	.csrf().disable()
			.authorizeRequests(
				a -> a.antMatchers("/", "/error", "/webjars/**")
				.permitAll().anyRequest().authenticated()
			)
			.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
			.oauth2Login(
				o -> o.failureHandler((request, response, exception) -> {
					System.err.println(exception.getLocalizedMessage());
				    request.getSession().setAttribute("error.message", exception.getMessage());
	            })
				.defaultSuccessUrl(redirectUri)
				.userInfoEndpoint()
				.userService(customUserDetailsService)
				.oidcUserService(customOidcUserDetailsService)
			)
			.logout(l -> l
		            .logoutSuccessUrl(redirectUri).permitAll()
		            .deleteCookies("JSESSIONID")
		            .invalidateHttpSession(true)
		    );
	}

}
