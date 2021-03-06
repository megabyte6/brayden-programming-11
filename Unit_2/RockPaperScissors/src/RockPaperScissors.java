import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
This program plays rock, paper, scissors with you.
Just follow what is printed to the console.
*/

public class RockPaperScissors {
    // Main method to run main code
    public static void main(String[] args) {
        // Declare variables
        char computerChoice;                        // Will contains what the computer chooses
        char playerChoice;                          // Will contains what the player chooses
        String rawPlayerInput;                      // Will contain the raw input from the player
        char acceptedResponses[] = {'r', 'p', 's'}; // List of accepted responses
        // Create a scanner
        Scanner scanner = new Scanner(System.in);
        // Create random object
        Random rand = new Random();

        // Beginning of game
        // Print rule of the game
        System.out.println();
        System.out.println("Choose rock paper or scissors. 'r' for rock, 'p' for paper, or 's' for scissors.");
        /*
        Mr. Zaremba, you wrote "and 's' for scissors
        I had changed it to "or" instead of "and" as that is the correct term.
        You do, after all, want to choose one of them, not all of them.
        Hope you don't mind the change :)
        */
        
        // Get user input and check if it is in acceptedResponses
        rawPlayerInput = scanner.nextLine();
        // Close scanner
        scanner.close();
        if ((rawPlayerInput.length() != 1) || (!(new String(acceptedResponses).contains(rawPlayerInput)))) {
            System.out.println("Invalad selection, please play again.");
            System.exit(1);
        }
        playerChoice = rawPlayerInput.charAt(0);
        // Randomly generate computer choice
        computerChoice = acceptedResponses[rand.nextInt(3)];

        // Figure out who wins
        if (playerChoice == 'r') {
            if (computerChoice == 'r') {
                System.out.println("Draw!");
                System.out.print("Computer's Choice: rock");
            } else if (computerChoice == 'p') {
                System.out.println("You Lose!");
                System.out.print("Computer's Choice: paper");
            } else if (computerChoice == 's') {
                System.out.println("You Win!");
                System.out.print("Computer's Choice: scissors");
            }
            System.out.println("\tPlayer's Choice: rock");
        } else if (playerChoice == 'p') {
            if (computerChoice == 'r') {
                System.out.println("You Win!");
                System.out.print("Computer's Choice: rock");
            } else if (computerChoice == 'p') {
                System.out.println("Draw!");
                System.out.print("Computer's Choice: paper");
            } else if (computerChoice == 's') {
                System.out.println("You Lose!");
                System.out.print("Computer's Choice: scissors");
            }
            System.out.println("\tPlayer's Choice: paper");
        } else if (playerChoice == 's') {
            if (computerChoice == 'r') {
                System.out.println("You Lose!");
                System.out.print("Computer's Choice: rock");
            } else if (computerChoice == 'p') {
                System.out.println("You Win!");
                System.out.print("Computer's Choice: paper");
            } else if (computerChoice == 's') {
                System.out.println("Draw!");
                System.out.print("Computer's Choice: scissors");
            }
            System.out.println("\tPlayer's Choice: scissors");
        }
    }
}