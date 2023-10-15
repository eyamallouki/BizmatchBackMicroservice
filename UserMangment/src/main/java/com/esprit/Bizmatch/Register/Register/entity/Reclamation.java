package com.esprit.Bizmatch.Register.Register.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idClaim;
    @Enumerated(EnumType.STRING)
    private TypeClaim typeClaim;

    private String contenu;
   @OneToOne(cascade = CascadeType.ALL)
    private User reclamed;
    @ManyToOne(cascade = CascadeType.ALL)
    private User reclamant;
    private Date date;
}
