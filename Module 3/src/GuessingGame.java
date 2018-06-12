import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        // Use a Scanner to input integer values
        Scanner input = new Scanner( System.in );
        String again = "y";

        while(again.equals("y")) {
            System.out.println("What range would you like to guess: ");
            int range = input.nextInt();
            System.out.println("How many guesses would you like: ");
            int guesses = input.nextInt();
            int answer = (int) (range * Math.random()) + 1;
            int guess = -1;

            while(guess != answer && guesses > 0) {
                System.out.println("What is your guess: ");
                guess = input.nextInt();
                guesses--;

                if(guess > answer) {
                    System.out.println("Too high");
                    System.out.println(String.format("%d guesses left!", guesses));
                }
                else if(guess < answer) {
                    System.out.println("Too low");
                    System.out.println(String.format("%d guesses left!", guesses));
                }
            }

            if(guess == answer) {
                System.out.println("You win! :D");
            }
            else {
                System.out.println("You lose! D:");
            }

            System.out.println("Would you like to play again? (y/n): ");
            again = input.next();
        }

        System.out.println("Goodbye!");
    }
}
