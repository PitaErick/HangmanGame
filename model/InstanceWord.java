package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * The 'InstanceWord' class represents a game instance of word guessing.
 * It offers methods to initialize, reset, and verify letters in the word.
 * 
 * @author Y2K
 */
public class InstanceWord {

    private static InstanceWord instanceWord;
    private String word;
    private char arr[];

    /**
     * Private constructor to ensure a singleton instance and initialize a random
     * word.
     */
    private InstanceWord() {
        this.word = getRandomWord();
        setUnderscores();

    }

    /**
     * Get the singleton instance of `InstanceWord`.
     *
     * @return The `InstanceWord` instance.
     */
    public static InstanceWord getInstance() {
        if (instanceWord == null) {
            instanceWord = new InstanceWord();
        }
        return instanceWord;
    }

    /**
     * Get the current word to be guessed.
     *
     * @return The word.
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Get a random word from a file containing a list of words.
     *
     * @return A random word.
     */
    private String getRandomWord() {
        List<String> word = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(getClass().getResource("/model/words.txt").getFile()));
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String linea;
            while ((linea = br.readLine()) != null) {
                word.add(String.format("%s", linea.toLowerCase(), StandardCharsets.UTF_8));
            }
            isr.close();
            br.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("file not found: " + e.getMessage());
        }
        return word.get(new Random().nextInt(word.size() - 0) + 0);
    }

    /**
     * Initialize the `arr` array with underscores to represent unguessed letters.
     */
    private void setUnderscores() {
        arr = new char[this.word.length()];
        for (int i = 0; i < this.word.length(); i++) {
            arr[i] = '_';
        }

    }

    /**
     * Get the current representation of the word with underscores for unguessed
     * letters.
     *
     * @return The word with underscores.
     */
    public String getUnderscores() {
        return new String(arr);
    }

    /**
     * Reset the word by selecting a new random word and updating the underscores.
     */
    public void resetWord() {
        String x = "";
        if (!this.word.equals(x = getRandomWord())) {
            this.word = x;
            setUnderscores();
        } else {
            resetWord();
        }
    }

    /**
     * Check if a guessed letter is in the word and update the representation if it
     * is.
     *
     * @param letterInput The guessed letter.
     * @return The updated word representation with underscores or null if the
     *         letter is not in the word.
     */
    public String checkLetter(String letterInput) {
        if (this.word.contains(letterInput)) {
            for (int i = 0; i < this.word.length(); i++) {
                if (checkLetter(this.word.charAt(i), letterInput.charAt(0))) {
                    arr[i] = this.word.charAt(i);
                }
            }
            return new String(arr);
        } else {
            return null;
        }
    }

    /**
     * Check if two characters are equal, taking character encoding into account.
     *
     * @param char1 The first character.
     * @param char2 The second character.
     * @return True if the characters are equal, false otherwise.
     */
    private boolean checkLetter(char char1, char char2) {
        return String.format("%c", (char) char1, StandardCharsets.UTF_8)
                .equals(String.format("%c", (char) char2, StandardCharsets.UTF_8));
    }

}
