package kr.co.woosuk.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import kr.co.woosuk.oauth.entity.CustomClientDetails;
import kr.co.woosuk.oauth.repository.CustomClientDetailsRepository;

@Service("customClientDetailsService")
public class CustomClientDetailsServiceImpl implements ClientDetailsService{

	@Autowired
	private CustomClientDetailsRepository customClientDetailsRepository;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		// TODO Auto-generated method stub
		CustomClientDetails client = customClientDetailsRepository.findByClientId(clientId);
		
		/*String resourceIds = client.getResourceIds().stream().collect(Collectors.joining(","));
        String scopes = client.getScope().split(",");
        String grantTypes = client.getAuthorizedGrantTypes().stream().collect(Collectors.joining(","));
        String authorities = client.getAuthorities().stream().collect(Collectors.joining(","));*/
        
		BaseClientDetails base = new BaseClientDetails(client.getClientId(), client.getResourceIds(), client.getScope(), client.getAuthorizedGrantTypes(), client.getAuthorities());
		base.setClientSecret(client.getClientSecret());
        base.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        base.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        //base.setAdditionalInformation(client.getAdditionalInformation());
        //base.setAutoApproveScopes(client.getScope());
        
		return base;
	}

	
}
