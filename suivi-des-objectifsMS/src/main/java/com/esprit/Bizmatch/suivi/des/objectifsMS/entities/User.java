package com.esprit.Bizmatch.suivi.des.objectifsMS.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User implements Serializable {

    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    @Email
    private String userEmail;
    private int isverified;
    private String verificationToken;
    @Pattern(regexp = "[0-9]{8}", message = "Le numéro doit être composé de 8 chiffres")
    private String userNumber;
    private String userCode;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;



}
