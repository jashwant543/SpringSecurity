package com.jashwant.kumar.spring_security.springSecurity.dto;

import lombok.*;

@AllArgsConstructor
@Getter
public class LoginResponseDto {
    private String email;
    private String refreshToke;
    private String accessToken;
}
