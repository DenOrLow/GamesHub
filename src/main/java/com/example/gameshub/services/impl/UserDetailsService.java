package com.example.gameshub.services.impl;


import com.example.gameshub.domain.models.User;
import com.example.gameshub.adapter.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .authorities(user.getAuthorities())
                .password(user.getPassword())
                .build();
    }
}
