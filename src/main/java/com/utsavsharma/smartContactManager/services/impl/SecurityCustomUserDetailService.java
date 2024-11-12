package com.utsavsharma.smartContactManager.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utsavsharma.smartContactManager.repositories.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;

    public SecurityCustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("this is security custom user detail service" + username);

        return userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + username + "' not found"));
    }

}
