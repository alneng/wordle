import java.util.ArrayList;
import java.util.Hashtable;

public class Game {

    boolean ongoing;
    int usedAttempts;
    final String word;
    String[] attemptedWords;
    ArrayList<String> usedLetters;
    Hashtable<String, String> hashtbl;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    public Game(String word) {
        this.ongoing = true;
        this.usedAttempts = 0;
        this.word = word;
        this.attemptedWords = new String[6];
        this.usedLetters = new ArrayList<String>();
        this.hashtbl = new Hashtable<String, String>();
    }

    public void makeGuess(String guess) {
        if (!Words.verifyLength(guess)) System.out.println("That word is invalid");
        else if (Words.checkIfUsed(this.attemptedWords, guess)) System.out.println("You already used that word");
        else {
            this.usedAttempts++;
            addAttemptedWord(guess);
            this.usedLetters.addAll(Words.getUniqueLetters(guess));
            this.usedLetters = Words.removeDuplicatesFromArr(this.usedLetters);
            this.hashtbl.put(guess, Words.getColorData(guess, this.word));

            if (this.word.equals(guess)) {
                ongoing = false;
                displayWin();
            } else {
                displayGame();
            }
        }
    }

    public void addAttemptedWord(String word) {
        this.attemptedWords[this.usedAttempts - 1] = word;
    }

    public void displayGame() {
        displayWords();
        displayKeyboard();
        System.out.println("-----------------------------------------------");
    }

    public void displayWin() {
        displayWords();
        System.out.println("You guessed the word '" + this.word + "' in " + this.usedAttempts + " attempts!");
    }

    public void displayLoss() {
        displayWords();
        System.out.println("You ran out of guesses!");
        System.out.println("The word was '" + this.word + "'");
    }

    public void displayWords() {
        for (int row = 0; row < 6; row++) {
            if (this.attemptedWords[row] != null) {
                for (int chr = 0; chr < 5; chr++) {
                    if (Words.getChar(hashtbl.get(this.attemptedWords[row]), chr).equals("G")) {
                        System.out.print(ANSI_GREEN_BACKGROUND + Words.getChar(this.attemptedWords[row], chr)
                                + ANSI_RESET);
                    } else if (Words.getChar(hashtbl.get(this.attemptedWords[row]), chr).equals("Y")) {
                        System.out.print(ANSI_YELLOW_BACKGROUND + Words.getChar(this.attemptedWords[row], chr)
                                + ANSI_RESET);
                    } else {
                        System.out.print(Words.getChar(this.attemptedWords[row], chr));
                    }
                }
                System.out.println("");
            } else {
                System.out.println("_____");
            }
        }
    }

    public void displayKeyboard() {
        String[] kb = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};

        for (String letter : Words.stringToArray(kb[0])) {
            if (this.usedLetters.contains(letter.toLowerCase()))
                System.out.print("_ ");
            else
                System.out.print(letter + " ");
        }
        System.out.println("");

        System.out.print(" ");
        for (String letter : Words.stringToArray(kb[1])) {
            if (this.usedLetters.contains(letter.toLowerCase()))
                System.out.print("_ ");
            else
                System.out.print(letter + " ");
        }
        System.out.println("");

        System.out.print("  ");
        for (String letter : Words.stringToArray(kb[2])) {
            if (this.usedLetters.contains(letter.toLowerCase()))
                System.out.print("_ ");
            else
                System.out.print(letter + " ");
        }
        System.out.println("");
    }
}