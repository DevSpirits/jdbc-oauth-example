package kr.co.woosuk.oauth.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.woosuk.oauth.entity.Role;
import kr.co.woosuk.oauth.entity.User;
import kr.co.woosuk.oauth.repository.RoleRepository;
import kr.co.woosuk.oauth.repository.UserRepository;
import kr.co.woosuk.oauth.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserDetailsService,UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) throws Exception {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		List<Role> userRole = roleRepository.findByUsername(username);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),getAuthorities(userRole));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(Role role: roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			
			System.out.println("roleName : " + role.getRoleName());
			
		}
		
		return authorities;
	}
	
}
