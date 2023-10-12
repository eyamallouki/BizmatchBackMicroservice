package com.esprit.Bizmatch.Register.Register.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PourcentageSentiment {
    private double pourcentagePositif;
    private double pourcentageNegatif;

    public PourcentageSentiment(double pourcentagePositif, double pourcentageNegatif) {
        this.pourcentagePositif = pourcentagePositif;
        this.pourcentageNegatif = pourcentageNegatif;
    }
}
