package com.esprit.Bizmatch.CRM.CRM.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
public class AnalyseSentiment {
    private Integer motsPositifs;
    private Integer motsNegatifs;


    public AnalyseSentiment(Integer motsPositifsCount, Integer motsNegatifsCount) {
        this.motsPositifs = motsPositifsCount;
        this.motsNegatifs = motsNegatifsCount;

    }




}
