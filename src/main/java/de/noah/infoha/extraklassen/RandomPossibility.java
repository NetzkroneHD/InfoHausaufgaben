package de.noah.infoha.extraklassen;

import java.util.Random;

public class RandomPossibility {

    private static final Random random = new Random();

    private double possibility;

    public RandomPossibility(double possibility) {
        if(!(possibility > 0 && possibility <= 1)) throw new IllegalStateException("'possibility' has to be between 0 and 1");
        this.possibility = possibility;
    }

    public boolean apply() {
        return (random.nextDouble() <= possibility);
    }

    public double getPossibility() {
        return possibility;
    }

    public void setPossibility(double possibility) {
        this.possibility = possibility;
    }
}
