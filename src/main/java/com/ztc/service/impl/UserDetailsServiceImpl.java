package com.ztc.service.impl;

import com.ztc.dao.UserRepository;
import com.ztc.entity.AccountCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by zt 2018/5/19 19:32
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountCredentials accountCredentials = userRepository.findByUsername(username);
        if (accountCredentials == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(accountCredentials.getUsername(), accountCredentials.getPassword(), new ArrayList<>());
    }

}
