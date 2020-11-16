package com.goodbookclub.bookclub.converters;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;
import com.goodbookclub.bookclub.domains.User;
import com.goodbookclub.bookclub.services.security.Authentication.UserDetailImpl;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

	@Override
	public UserDetails convert(User src) {
		// TODO Auto-generated method stub
		UserDetailImpl ud = new UserDetailImpl();
		if(src!=null) {			
			ud.setUsername(src.getUsername());
			ud.setPassword(src.getEncryptedpassword());
			ud.setEnabled(src.isEnabled());
			
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			
			authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
			ud.setAuthority(authorities);
		}
		
		return ud;
	}
}
