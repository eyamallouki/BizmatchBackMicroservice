package com.Bizmatch.PrestationServiceMS.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Role implements Serializable {

    @Id
    private String roleName;
    private String roleDescription;

}
