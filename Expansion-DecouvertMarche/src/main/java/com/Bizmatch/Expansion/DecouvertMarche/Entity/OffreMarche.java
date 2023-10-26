package com.Bizmatch.Expansion.DecouvertMarche.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class OffreMarche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idoffre;
    private  String location;
    private  String nomOffre ;
    private  String detailsOffre;
    private  Integer capacite ;
    private Integer price;
}
