import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        // Use a Scanner to input integer values
        Scanner input = new Scanner( System.in );
        String again = "y";

        // run as long as user responds y to continue prompt
        while(again.equals("y")) {
            System.out.println("What range would you like to guess: ");
            int range = input.nextInt();
            System.out.println("How many guesses would you like: ");
            int guesses = input.nextInt();
            int answer = (int) (range * Math.random()) + 1;
            int guess = -1;

            // run game so long as player has guesses left and hasn't
            // guessed the right number
            while(guess != answer && guesses > 0) {
                System.out.println("What is your guess: ");
                guess = input.nextInt();
                guesses--;

                // only prompt user if guess is not correct
                if(guess > answer) {
                    System.out.println("Too high");
                    System.out.println(String.format("%d guesses left!", guesses));
                }
                else if(guess < answer) {
                    System.out.println("Too low");
                    System.out.println(String.format("%d guesses left!", guesses));
                }
            }

            String result = guess == answer ? "You win! :D" : "You lost! D:";
            System.out.println(result + "\nWould you like to play again? (y/n): ");
            again = input.next();
        }

        System.out.println("Goodbye!");
    }
}
