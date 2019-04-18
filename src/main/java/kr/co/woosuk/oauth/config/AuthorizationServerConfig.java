package kr.co.woosuk.oauth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import kr.co.woosuk.oauth.service.impl.CustomClientDetailsServiceImpl;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomClientDetailsServiceImpl customClientDetailsService;
	
	//@Autowired
	//@Qualifier("ClientDetailServiceImpl")
	//private ClientDetailsService clientDetailsService;
	
	@Autowired
	private TokenStore tokenStore;
	
	//@Autowired
	//private DataSource dataSource;
	
	/**
	 *  a configurer that defines the client details service. Client details can be initialized, or you can just refer to an existing store.
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.withClientDetails(customClientDetailsService);
	}
	
	/**
	 *  defines the security constraints on the token endpoint.
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
		security.checkTokenAccess("permitAll()");
	}
	
	/**
	 *  defines the authorization and token endpoints and the token services.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		endpoints.tokenStore(tokenStore)
				 .authenticationManager(authenticationManager);
	}
	
}
