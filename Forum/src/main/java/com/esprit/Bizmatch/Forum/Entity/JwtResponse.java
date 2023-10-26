package com.esprit.Bizmatch.Forum.Entity;

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
