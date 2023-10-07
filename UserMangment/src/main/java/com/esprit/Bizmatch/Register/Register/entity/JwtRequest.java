package com.esprit.Bizmatch.Register.Register.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtRequest {

    private String userName;
    private String userPassword;


}
