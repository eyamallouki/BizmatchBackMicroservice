package com.Bizmatch.Expansion.DecouvertMarche.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idreservation;
    private String nomreservation;
    @Temporal(TemporalType.DATE)
    private Date datereservation;
    @ManyToMany
    private List<User> userreser;
    @ManyToMany
    private List<OffreMarche>offremarches;
}
