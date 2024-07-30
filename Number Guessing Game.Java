import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You will have 10 attempts to guess a number between 1 and 100.");

        // Keep the game running until the user decides to quit
        String playAgain;
        do {
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int attempts = 0;
            int score = 0;
            int guess = 0;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                attempts++;

 if (guess < numberToGuess) {
                    System.out.println("Your guess is too low.");
                } else if (guess > numberToGuess) {+F%
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Congratulations! You guessed the number!");
                    score = maxAttempts - attempts + 1; // Points based on remaining attempts
                    System.out.println("You scored " + score + " points this round.");
                    break;
                }
            }

            if (guess != numberToGuess) {
if (guess != numberToGuess) {
                System.out.println("You've used all your attempts. The number was " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next();
        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thank you for playing the Number Guessing Game!");
        scanner.close();
    }
}