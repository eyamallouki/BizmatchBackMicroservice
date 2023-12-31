package com.Bizmatch.PrestationServiceMS.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private User user;
    private String jwtToken;

}
