package com.jashwant.kumar.spring_security.springSecurity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginDto {

    private String email;
    private String password;


}
