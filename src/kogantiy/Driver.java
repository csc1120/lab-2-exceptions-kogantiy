/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Yuktha
 * Last Updated: 9/18/2024
 */
package kogantiy;

import java.util.Scanner;

public class Driver {
    private static final int MIN_DICE = 2;
    private static final int MAX_DICE = 10;
    private static final int MIN_SIDES = 2;
    private static final int MAX_SIDES = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean successful = false;
        while (!successful) {
            try {
                int[] config = getInput(scanner);
                Die[] dice = createDice(config[0], config[1]);
                int[] results = rollDice(dice, config[2]);
                int maxIndex = findMax(results);
                report(results);
                successful = true; // Exit loop if everything was successful
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }

    private static int[] getInput(Scanner scanner) {
        System.out.println("Enter configuration:");
        String[] parts = scanner.nextLine().trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid input: Expected 3 values but only received " + parts.length);
        }
        int numDice = Integer.parseInt(parts[0]);
        int numSides = Integer.parseInt(parts[1]);
        int numRolls = Integer.parseInt(parts[2]);
        if (numDice < MIN_DICE || numDice > MAX_DICE || numSides < MIN_SIDES || numSides > MAX_SIDES) {
            throw new IllegalArgumentException("Bad die creation: Illegal number of sides or number of dice");
        }
        return new int[] { numDice, numSides, numRolls };
    }

    private static Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    private static int[] rollDice(Die[] dice, int numRolls) {
        int[] results = new int[MAX_DICE * MAX_SIDES + 1];
        for (int roll = 0; roll < numRolls; roll++) {
            int sum = 0;
            for (Die die : dice) {
                die.roll();
                sum += die.getCurrentValue();
            }
            results[sum]++;
        }
        return results;
    }

    private static int findMax(int[] results) {
        int maxCount = 0;
        for (int count : results) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    private static void report(int[] results) {
        for (int i = 0; i < results.length; i++) {
            if (results[i] > 0) {
                System.out.print(i + " : ");
                for (int j = 0; j < results[i]; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }
}