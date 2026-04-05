package com.jashwant.kumar.spring_security.springSecurity.services;

import com.jashwant.kumar.spring_security.springSecurity.exceptions.ResourceNotFoundException;
import com.jashwant.kumar.spring_security.springSecurity.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByEmail(userName).
                orElseThrow(() -> new ResourceNotFoundException("user not found "+ userName));
    }
}
