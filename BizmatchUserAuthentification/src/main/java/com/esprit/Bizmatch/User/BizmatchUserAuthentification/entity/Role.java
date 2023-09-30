package com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Role implements Serializable {

    @Id
    private String roleName;
    private String roleDescription;

}
