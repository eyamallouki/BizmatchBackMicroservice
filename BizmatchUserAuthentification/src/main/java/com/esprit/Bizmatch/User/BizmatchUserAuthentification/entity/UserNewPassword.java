package com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNewPassword {
    private String email;
    private String code;
    private String password;
}
