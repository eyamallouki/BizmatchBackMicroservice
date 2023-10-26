package com.esprit.Bizmatch.partenariatMS.persistence.entity;

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
