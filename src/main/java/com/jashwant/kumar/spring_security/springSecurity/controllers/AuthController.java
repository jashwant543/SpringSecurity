package com.jashwant.kumar.spring_security.springSecurity.controllers;

import com.jashwant.kumar.spring_security.springSecurity.dto.LoginDto;
import com.jashwant.kumar.spring_security.springSecurity.dto.SignUpDto;
import com.jashwant.kumar.spring_security.springSecurity.dto.UserDto;
import com.jashwant.kumar.spring_security.springSecurity.services.AuthService;
import com.jashwant.kumar.spring_security.springSecurity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private  final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto)
    {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginDto loginDto, HttpServletResponse response,

                                        HttpServletRequest request)
    {
        String token = authService.login(loginDto);

        Cookie cookie  = new Cookie("token",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return  ResponseEntity.ok(token);
    }
}
