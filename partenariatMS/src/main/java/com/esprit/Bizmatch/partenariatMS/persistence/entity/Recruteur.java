package com.esprit.Bizmatch.partenariatMS.persistence.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Recruteur implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer idR ;

    private String userName;

 //   @NotBlank(message = "ce champ ne doit pas être vide")
    private String nom;

   // @NotBlank(message = "ce champ ne doit pas être vide")
    private String prenom;

  // @Email
    //@NotBlank(message = "ce champ ne doit pas être vide")
    private String email;
   // @NotNull(message = "ce champ ne doit pas être vide")
    private Double telephone;
    private String dure;
    private String typeRec;

   // @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruteur")
   // private List<Entreprise> entreprises;
}
