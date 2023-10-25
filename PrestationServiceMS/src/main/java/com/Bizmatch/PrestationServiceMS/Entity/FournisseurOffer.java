package com.Bizmatch.PrestationServiceMS.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FournisseurOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idfournisseur ;
    private  String domainesactivite ;
    private  String details ;
    private String username ;
    private String prenom ;
    private String email ;
    private  String address ;
    private String partenaire ;

    @ManyToMany
    private List<User>usersfournisseur ;



}
