package com.goodbookclub.bookclub.services.security.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.goodbookclub.bookclub.domains.User;
import com.goodbookclub.bookclub.repositories.UserRepository;

import org.springframework.core.convert.converter.Converter;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private UserRepository userService;
    private Converter<User, UserDetails> userUserDetailsConverter;

    @Autowired
    public void setUserService(UserRepository userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "userToUserDetails")
    public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
        this.userUserDetailsConverter = userUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userUserDetailsConverter.convert(userService.findByUsername(username));
    }
}
