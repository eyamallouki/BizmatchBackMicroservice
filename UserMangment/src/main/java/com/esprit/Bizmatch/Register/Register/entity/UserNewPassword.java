package com.esprit.Bizmatch.Register.Register.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNewPassword {
    private String email;
    private String code;
    private String password;
}
