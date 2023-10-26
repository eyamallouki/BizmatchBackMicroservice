package com.esprit.Bizmatch.partenariatMS.persistence.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Partenariat")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class DemandeAchat implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //@Column(name = "ID")
    protected Integer id ;

   // @NotBlank(message = "ce champ ne doit pas être vide")
    private String nom;

   // @NotBlank(message = "ce champ ne doit pas être vide")
    private String adresse;

 //  @Email
   // @NotBlank(message = "ce champ ne doit pas être vide")
    private String siteWeb;

   // @NotNull(message = "ce champ ne doit pas être vide")

    private Double budget;


   // @OneToMany(fetch = FetchType.LAZY, mappedBy = "demandeAchat")
  //  private List<Entreprise> entreprises;

    private String dure;
    private String typePart;

}