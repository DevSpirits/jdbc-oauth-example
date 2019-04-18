package kr.co.woosuk.oauth.config;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Resource(name = "userService")
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
    public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
    }
	
	/*@Bean
	public ApprovalStore approvalStore() {
		return new JdbcApprovalStore(dataSource);
	}*/
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
			.antMatchers("/home","/webjars/**","/css/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/login/login")
			.permitAll()
			.and()
		.csrf()
			.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/users"))
			.disable()
			.logout()
			.permitAll();
		
		/*http.csrf().disable()
			.anonymous().disable()
			.authorizeRequests()
			.antMatchers("/api-docs/*").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/oauth_login");*/
		
	}

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	
    	CorsConfiguration config = new CorsConfiguration();
    	
    	config.setAllowedOrigins(Arrays.asList("*"));
    	config.setAllowedMethods(Arrays.asList("*"));
    	config.setAllowedHeaders(Arrays.asList("*"));
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", config);
    	
    	return source;
    }
	
}
