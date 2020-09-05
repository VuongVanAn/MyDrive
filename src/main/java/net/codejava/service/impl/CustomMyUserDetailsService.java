package net.codejava.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.codejava.model.User;
import net.codejava.repository.UserRepository;

@Service
public class CustomMyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getGrantedAuthority(user));
	}
	
	private Collection<GrantedAuthority> getGrantedAuthority(User user) {
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		if (user.getPermission() == 1) {
			grantedAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			grantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
		} 
		return grantedAuthority;
	}

}
