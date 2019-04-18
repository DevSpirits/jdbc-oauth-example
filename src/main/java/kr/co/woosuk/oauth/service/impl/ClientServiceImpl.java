package kr.co.woosuk.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.woosuk.oauth.entity.CustomClientDetails;
import kr.co.woosuk.oauth.repository.CustomClientDetailsRepository;
import kr.co.woosuk.oauth.service.ClientService;

@Service("clientService")
public class ClientServiceImpl implements ClientService{

	@Autowired
	private CustomClientDetailsRepository customClientDetailsRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public CustomClientDetails createClientDetail(CustomClientDetails customClientDetails) {
		// TODO Auto-generated method stub
		
		customClientDetails.setClientSecret(passwordEncoder.encode(customClientDetails.getClientSecret()));
		
		return customClientDetailsRepository.save(customClientDetails);
	}
	
}
