package com.esprit.Bizmatch.suivi.des.objectifsMS.entities;

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
