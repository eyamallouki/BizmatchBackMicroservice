package com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UserCode {
    @Id
    private int id;
    public static String getCode() {
        return UUID.randomUUID().toString();
    }
}
