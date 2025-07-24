import java.util.Random;
import java.util.Scanner;

public class guessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int numberToGuess = random.nextInt(100) + 1;
        int Attempts = 0;
        int Guess;
        System.out.println("Welcome to Guess The Number!");
        System.out.println("I'm thinking of a number between 1 and 100. Try to guess it!");

        do {
            System.out.print("Enter your guess: ");
            Guess = sc.nextInt();
            Attempts++;
            if (Guess < numberToGuess) {
                System.out.println("Too small. Guess again!");
            } else if (Guess > numberToGuess) {
                System.out.println("Too large. Guess again!");
            } else {
                System.out.println("Congratulations! You guessed the number in " + Attempts + " attempts.");
            }

        } while (Guess != numberToGuess);
    }
}