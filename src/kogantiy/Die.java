/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Yuktha
 * Last Updated: 9/18/2024
 */
package kogantiy;

import java.util.Random;

public class Die {
    private int numSides;
    private int currentValue;
    private Random random;

    public Die(int numSides) {
        if (numSides < 2 || numSides > 100) {
            throw new IllegalArgumentException("Illegal number of sides: " + numSides);
        }
        this.numSides = numSides;
        this.currentValue = 0; // Die is not rolled yet.
        this.random = new Random();
    }

    public int getCurrentValue() {
        if (currentValue == 0) {
            throw new DieNotRolledException("Die has not been rolled yet");
        }
        return currentValue;
    }

    public void roll() {
        currentValue = random.nextInt(numSides) + 1;
    }
}