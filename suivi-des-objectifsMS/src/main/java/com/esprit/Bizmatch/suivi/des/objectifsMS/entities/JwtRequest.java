package com.esprit.Bizmatch.suivi.des.objectifsMS.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class JwtRequest {

    private String userName;
    private String userPassword;


}
