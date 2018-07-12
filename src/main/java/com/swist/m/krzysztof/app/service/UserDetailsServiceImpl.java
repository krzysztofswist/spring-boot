package com.swist.m.krzysztof.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swist.m.krzysztof.app.dao.model.AppUser;
import com.swist.m.krzysztof.app.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<AppUser> optionalAppUser = userRepository.findById(userName);

		if (!optionalAppUser.isPresent()) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		AppUser appUser = optionalAppUser.get();

		System.out.println("Found User: " + appUser);
		List<String> roleNames = null;
		// [ROLE_USER, ROLE_ADMIN,..]
		if (appUser.getUserName().equals("krzysztof")) {
			roleNames = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
		} else {

			roleNames = Arrays.asList("ROLE_USER");
		}

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
				appUser.getEncrytedPassword(), grantList);

		return userDetails;
	}

}
