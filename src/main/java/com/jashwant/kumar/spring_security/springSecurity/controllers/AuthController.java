package com.jashwant.kumar.spring_security.springSecurity.controllers;

import com.jashwant.kumar.spring_security.springSecurity.dto.LoginDto;
import com.jashwant.kumar.spring_security.springSecurity.dto.LoginResponseDto;
import com.jashwant.kumar.spring_security.springSecurity.dto.SignUpDto;
import com.jashwant.kumar.spring_security.springSecurity.dto.UserDto;
import com.jashwant.kumar.spring_security.springSecurity.services.AuthService;
import com.jashwant.kumar.spring_security.springSecurity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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
    public ResponseEntity<LoginResponseDto> logIn(@RequestBody LoginDto loginDto, HttpServletResponse response,

                                                  HttpServletRequest request)
    {
     LoginResponseDto loginResponseDto = authService.login(loginDto);
     String accessToken = loginResponseDto.getAccessToken();
     String refreshToken  = loginResponseDto.getRefreshToke();

        Cookie cookie  = new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return  ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request)
    {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie->"refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found inside the cookies"));
        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }

}
