package com.esprit.Bizmatch.suivi.des.objectifsMS.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Objectif implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObjectif;
    private String userName;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Pirorite pirorite;
    @CreationTimestamp
    private LocalDate dateRealisation;

    private LocalDate startDate;
    private LocalDate endDate;
}
