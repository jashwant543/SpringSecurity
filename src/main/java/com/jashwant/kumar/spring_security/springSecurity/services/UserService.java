package com.jashwant.kumar.spring_security.springSecurity.services;

import com.jashwant.kumar.spring_security.springSecurity.dto.LoginDto;
import com.jashwant.kumar.spring_security.springSecurity.dto.SignUpDto;
import com.jashwant.kumar.spring_security.springSecurity.dto.UserDto;
import com.jashwant.kumar.spring_security.springSecurity.entities.User;
import com.jashwant.kumar.spring_security.springSecurity.exceptions.ResourceNotFoundException;
import com.jashwant.kumar.spring_security.springSecurity.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data

@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByEmail(userName).
                orElseThrow(() -> new BadCredentialsException("user not found "+ userName));
    }

    public User getUserById(Long userId ) throws UsernameNotFoundException {
        return userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("user not found "+ userId));
    }

    public UserDto signUp(SignUpDto signUpDto) {

        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent())
        {
            throw  new BadCredentialsException("User already exist");
        }
        User userToCreated = modelMapper.map(signUpDto,User.class);
        userToCreated.setPassword(passwordEncoder.encode(userToCreated.getPassword()));

        User savedUser = userRepository.save(userToCreated);

        return modelMapper.map(savedUser,UserDto.class);


    }


}
