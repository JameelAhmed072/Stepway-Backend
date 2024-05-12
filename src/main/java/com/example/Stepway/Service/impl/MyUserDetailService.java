package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.User;
import com.example.Stepway.Repository.UserRepository;
import com.example.Stepway.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("Wrong Credentials"+email);
        }
        return new CustomUserDetails(user);
    }

}
