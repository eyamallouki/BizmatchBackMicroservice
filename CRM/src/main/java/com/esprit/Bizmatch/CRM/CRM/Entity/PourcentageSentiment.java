package com.esprit.Bizmatch.CRM.CRM.Entity;

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
