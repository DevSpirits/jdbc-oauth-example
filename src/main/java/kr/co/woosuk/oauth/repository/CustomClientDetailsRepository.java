package kr.co.woosuk.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.woosuk.oauth.entity.CustomClientDetails;

@Repository
public interface CustomClientDetailsRepository extends JpaRepository<CustomClientDetails, String>{

	CustomClientDetails findByClientId(String clientId);
	
}
