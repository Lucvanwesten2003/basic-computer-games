import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Game of Letter
 * <p>
 * Based on the Basic game of Letter here
 * https://github.com/coding-horror/basic-computer-games/blob/main/54%20Letter/letter.bas
 * <p>
 * Note: The idea was to create a version of the 1970's Basic game in Java,
 * without introducing
 * new features - no additional text, error checking, etc has been added.
 */
public class Letter {

    public static final int OPTIMAL_GUESSES = 5;
    public static final int ASCII_A = 65;
    public static final int ALL_LETTERS = 26;

    // Used for keyboard input
    private final Scanner kbScanner;

    // Current game statea

    // Players guess count;
    private int playerGuesses;

    // Computers ascii code for a random letter between A..Z
    private int computersLetter;

    public Letter() {
        // Initialise kb scanner
        kbScanner = new Scanner(System.in);
    }

    public void startUp() {
        intro();
        this.init();
    }

    public void init() {
        playerGuesses = 0;
        computersLetter = ASCII_A + (int) (Math.random() * ALL_LETTERS);
        System.out.println("O.K., I HAVE A LETTER.  START GUESSING.");
        this.guessing();
    }

    public void guessing() {

        String playerGuess = displayTextAndGetInput("WHAT IS YOUR GUESS? ").toUpperCase();
        
        // Convert first character of input string to ascii
        int toAscii = playerGuess.charAt(0);
        playerGuesses++;
        if (toAscii == computersLetter) {
            this.results();
        }
        if (toAscii > computersLetter) {
            System.out.println("TOO HIGH.  TRY A LOWER LETTER.");
        } else {
            System.out.println("TOO LOW.  TRY A HIGHER LETTER.");
        }
        this.guessing();
    }

    public void results() {
        System.out.println();
        System.out.println("YOU GOT IT IN " + playerGuesses + " GUESSES!!");
        if (playerGuesses <= OPTIMAL_GUESSES) {
            System.out.println("GOOD JOB !!!!!");
            Toolkit.getDefaultToolkit().beep();
        } else {
            // Took more than optimal number of guesses
            System.out.println("BUT IT SHOULDN'T TAKE MORE THAN " + OPTIMAL_GUESSES + " GUESSES!");
        }
        System.out.println();
        System.out.println("LET'S PLAN AGAIN.....");
        this.init();
    }

    public void intro() {
        System.out.println(simulateTabs(33) + "LETTER");
        System.out.println(simulateTabs(15) + "CREATIVE COMPUTING  MORRISTOWN, NEW JERSEY");
        System.out.println();
        System.out.println("LETTER GUESSING GAME");
        System.out.println();
        System.out.println("I'LL THINK OF A LETTER OF THE ALPHABET, A TO Z.");
        System.out.println("TRY TO GUESS MY LETTER AND I'LL GIVE YOU CLUES");
        System.out.println("AS TO HOW CLOSE YOU'RE GETTING TO MY LETTER.");
    }

    /**
     * Simulate the old basic tab(xx) command which indented text by xx spaces.
     *
     * @param spaces number of spaces required
     * @return String with number of spaces
     */
    private String simulateTabs(int spaces) {
        char[] spacesTemp = new char[spaces];
        Arrays.fill(spacesTemp, ' ');
        return new String(spacesTemp);
    }

    /*
     * Print a message on the screen, then accept input from Keyboard.
     *
     * @param text message to be displayed on screen.
     * 
     * @return what was typed by the player.
     */
    private String displayTextAndGetInput(String text) {
        System.out.print(text);
        return kbScanner.next();
    }
}