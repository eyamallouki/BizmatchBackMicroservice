package com.esprit.Bizmatch.Register.Register.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtResponse {

    private User user;
    private String jwtToken;


    public JwtResponse(User user, String newGeneratedToken) {
    }
}
