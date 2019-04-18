package kr.co.woosuk.oauth.service;

import kr.co.woosuk.oauth.entity.User;

public interface UserService {

	User createUser(User user) throws Exception;
	
}
