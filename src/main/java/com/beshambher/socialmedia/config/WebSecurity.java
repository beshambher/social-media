package com.beshambher.socialmedia.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.beshambher.socialmedia.service.oauth.CustomOidcUserDetailsService;
import com.beshambher.socialmedia.service.oauth.CustomUserDetailsService;

import lombok.Getter;
import lombok.Setter;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Value("${server.auth.uri.redirect}")
	private String redirectUri;

	@Value("${server.auth.uri.origin}")
	private String[] originUrls;

	@Autowired
	private UrlProperties whitelistUrls;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private CustomOidcUserDetailsService customOidcUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.cors().and()
		 	.csrf().disable()
			.authorizeRequests(
				a -> a.antMatchers(whitelistUrls.getWhitelist())
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

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedOrigins(Arrays.asList(originUrls));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Getter
	@Setter
	@Component
	@ConfigurationProperties("server.url")
	public class UrlProperties {

	    private String[] whitelist;

	}

}
