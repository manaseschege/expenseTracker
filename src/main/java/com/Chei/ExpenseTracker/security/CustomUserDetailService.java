package com.Chei.ExpenseTracker.security;

import com.Chei.ExpenseTracker.Entity.Users;
import com.Chei.ExpenseTracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
@Autowired
private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
Users existingUser=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found with email : "+email));
 return new org.springframework.security.core.userdetails.User(existingUser.getEmail(),existingUser.getPassword(),new ArrayList<>());

    }
}
