import java.util.Scanner;
import java.util.Random;

/**
* The program is a game where the player will roll 3 dice to get a total sum of 12 in order to win.
* 1. Player is asked to choose between one of three dices.
* 2. Player can only throw one dice each round, a dice which have not been used in the round yet.
* 3. Player wins the round if the sum of dices is 12, tie if below and looses the round if sum exceeds 12.
* 3. After each round, various results are printed to the user.
* 4. Win and loss counters continues over rounds and are saved until game is ended.
* 5. Game is ended when player types q.
* @author Hannes Tarach (hantar-3)
*/

class Main {
    static final String GAME_START = "Welcome to dice game 12. You must roll 1-3 dice and try to get the sum of 12 ...\n";
    static final String CHOOSE_DICE = "Enter which dice you want to roll [1,2,3] (exit with q):";
    static final String ROUND_WON = "You won!!";
    static final String ROUND_LOST = "You lost!!";
    static final String ROUND_TIE = "You neither won nor lost the game.";
    static final String NEXT_ROUND = "Next round!";
    static final String GAME_OVER = "Game Over!";
    static final String ALREADY_SELECTED_DICE = "Sorry, you have already rolled that dice. Try again";
    static final String INVALID_ENTRY = "Sorry, that is an invalid entry. Try again. Valid entries are 1, 2, 3, and q\n";
    static final String AMOUNT_WIN_STRING = " #win: ";
    static final String AMOUNT_LOST_STRING = " #loss: ";
    static final String SUM_STRING = " sum: ";
    static final int MAX_DICE_VALUE = 6;
    static final int MIN_DICE_VALUE = 1;
    static final int DICE_SUM_TARGET_VALUE = 12;

    public static void main(final String[] args) {
        // Value of dices 1-3 and wether they are rolled or not.
        int dice1Value = 0;
        boolean isDice1Rolled = false;
        int dice2Value = 0;
        boolean isDice2Rolled = false;
        int dice3Value = 0;
        boolean isDice3Rolled = false;

        // Sum of dice values and sum of win & losses.
        int sum = 0;
        int winCounter = 0; // The number of wins
        int lossCounter = 0; // The number of losses

        boolean isPlaying = true; // Whether the game is being played or not
        int diceNumber = 0; // What dice users choose.
        String userString;

        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();

        System.out.println(GAME_START);

        while (isPlaying) {
            System.out.println(CHOOSE_DICE);

            if (userInput.hasNextInt()) {
                diceNumber = userInput.nextInt();
                if (diceNumber < 1 || diceNumber > 3) {
                    System.out.println(INVALID_ENTRY);
                    continue;
                } else if (diceNumber == 1 && !isDice1Rolled) {
                    dice1Value = rand.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
                    isDice1Rolled = true;
                } else if (diceNumber == 2 && !isDice2Rolled) {
                    dice2Value = rand.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
                    isDice2Rolled = true;
                } else if (diceNumber == 3 && !isDice3Rolled) {
                    dice3Value = rand.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
                    isDice3Rolled = true;
                } else {
                    System.out.println(ALREADY_SELECTED_DICE);
                    continue;
                }

                // Ouputs current dice and sum values.
                sum = dice1Value + dice2Value + dice3Value;
                System.out.println(dice1Value + " " + dice2Value + " " + dice3Value + SUM_STRING + sum + AMOUNT_WIN_STRING + winCounter + AMOUNT_LOST_STRING + lossCounter);

                // Checks the status of the game and incrementing counters.
                if (isDice1Rolled && isDice2Rolled && isDice3Rolled) {
                    if (sum == DICE_SUM_TARGET_VALUE) {
                        System.out.println(ROUND_WON);
                        winCounter++;
                    } else if (sum < DICE_SUM_TARGET_VALUE) {
                        System.out.println(ROUND_TIE);
                    } else {
                        System.out.println(ROUND_LOST);
                        lossCounter++;
                    }
                    // Defensive way of reseting each round.
                    sum = 0;
                    isDice1Rolled = false;
                    isDice2Rolled = false;
                    isDice3Rolled = false;
                    dice1Value = 0;
                    dice2Value = 0;
                    dice3Value = 0;
                }
            // This block reads user input, if q it breaks the while loop.
            } else if (userInput.hasNext()) {
                userString = userInput.next();
                if (userString.equalsIgnoreCase("q")) {
                    System.out.println(GAME_OVER);
                    isPlaying = false;
                    break;
                } else {
                    System.out.println(INVALID_ENTRY);
                    continue;
                }
            }
        }

        userInput.close();
    }
}