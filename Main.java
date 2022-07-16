import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        Game currentGame = new Game("roomy");

        while (currentGame.ongoing && currentGame.usedAttempts < 6) {
            System.out.print("Guess a word: ");
            String guess = console.nextLine();
            currentGame.makeGuess(guess);
        }
        if (currentGame.ongoing)
            currentGame.displayLoss();
    }
}
