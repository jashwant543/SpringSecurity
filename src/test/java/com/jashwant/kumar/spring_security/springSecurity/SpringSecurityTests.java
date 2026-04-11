package com.jashwant.kumar.spring_security.springSecurity;

import com.jashwant.kumar.spring_security.springSecurity.entities.User;
import com.jashwant.kumar.spring_security.springSecurity.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityTests {

    @Autowired
    private JwtService jwtService;
    @Test
    void contextLoads() {

    }
}