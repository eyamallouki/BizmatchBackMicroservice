package com.esprit.Bizmatch.Register.Register.entity;

import lombok.Getter;
import lombok.Setter;


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
