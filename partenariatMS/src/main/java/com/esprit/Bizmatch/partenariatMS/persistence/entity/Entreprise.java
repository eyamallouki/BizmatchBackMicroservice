package com.esprit.Bizmatch.partenariatMS.persistence.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Entreprise {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    //@Pattern(regexp = "^[a-zA-Z\\s]*$")
    //@Column(name = "nom", nullable = false)
    private String nom;


    //@NotBlank(message = "ce champ ne doit pas être vide")
    //@Column(name = "image", nullable = false)
    private String photo;

    //@Pattern(regexp = "^[a-zA-Z\\s]*$")
    //@NotBlank(message = "ce champ ne doit pas être vide")
    private String adresse;


   // @Size(min = 10, message = "Le champ code doit avoir au minimum 10 caractères")
   // @NotBlank(message = "ce champ ne doit pas être vide")
    private String details;

   // @NotNull(message = "ce champ ne doit pas être vide")
  //  @Min(value = 0, message = "le budget doit être supérieur ou égal à zéro")
    @Column(name = "budget")
    private Double budget;

    private Boolean accepte;


    private String domaine;

  //  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
 //   private DemandeAchat demandeAchat;

   // @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  //  private Recruteur recruteur;




}
