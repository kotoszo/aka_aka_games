package com.aka.games;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import static java.lang.Math.toIntExact;

public class Hangman {

    String word;
    Character[] hiddenword;
    Character[][] matrix;

    public Hangman() {

        String[] wordList = getFile();
        this.word = getWord(wordList);
        this.hiddenword = hideWord(word);
        this.matrix = death();
    }

    private String[] getFile() {

        URL url = getClass().getResource("words.csv");
        File file = new File(url.getPath());
        int fileLength = toIntExact(file.length());

        int index = 0;
        String[] wordsList = new String[fileLength];

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                wordsList[index++] = scanner.next();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Meh");
        }
        return wordsList;

    }

    private String getWord(String[] wordList){

        Random random = new Random();
        int randomNr = random.nextInt(35);
        return wordList[randomNr];
    }

    private Character[] hideWord(String word) {
        Character[] hiddenWord = new Character[word.length()];

        for (int i=0; i < word.length(); i++){
            hiddenWord[i] = '_';
        }
        return hiddenWord;
    }

    public Character[][] death() {
        Character[][] matrix = new Character[12][12];
        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix[0].length; j++) {
                matrix[i][j] = ' ';
            }
        }
        for (int i=2; i < 7; i++){
            matrix[11][i] = '_';
        }
        return matrix;
    }

    private void printMatrix() {
        for (int x=0; x < 12; x++) {
            for (int y=0; y < 12; y++) {
                System.out.print(matrix[x][y]);
            }
            System.out.println();
        }
    }

    private void wayToHell(int steps){
        switch (steps){
            case 1:
                for (int x=11; x > 3; x--) {
                    matrix[x][4] = '|';
                }
                break;
            case 2:
                for (int y=4; y < 10 ; y++) {
                    matrix[4][y] = '_';
                }
                break;
            case 3:
                matrix[5][6] = '/';
                matrix[6][5] = '/';
                break;
            case 4:
                matrix[5][9] = '|';
                matrix[6][9] = '|';
                break;
            case 5:
                matrix[7][9] = 'O';
                break;
            case 6:
                matrix[8][9] = '|';
                matrix[9][9] = '|';
                break;
            case 7:
                matrix[8][10] = '\\';
                break;
            case 8:
                matrix[8][8] = '/';
                break;
            case 9:
                matrix[10][10] = '\\';
                break;
            case 10:
                matrix[10][8] = '/';
                break;
        }
    }

    public void appRun() {
        boolean isOver = false;
        int mistakes = 0;
        int tries = 0;
        ArrayList<Character> usedChars = new ArrayList<>();
        while (!isOver) {
            boolean isContain = false;
            printMatrix();
            System.out.println(Arrays.toString(hiddenword));
            System.out.println("Give me your shot bro!");
            Scanner hangManInput = new Scanner(System.in);
            char guess = hangManInput.next().charAt(0);
            if (Character.isDigit(guess)) {
                System.out.println("Digit? What were you thinking?");
            } else {
                if (!usedChars.contains(guess)) {
                    for (int i=0; i < word.length(); i++) {
                        if (word.charAt(i) == guess) {
                            hiddenword[i] = guess;
                            isContain = true;
                            tries++;
                        }
                    }
                    if (!isContain) {
                        usedChars.add(guess);
                        isContain = false;
                        mistakes++;
                        wayToHell(mistakes);
                    }
                }
            }
            System.out.println("Your already used characters: " + usedChars);
            if (mistakes == 10) {
                isOver = true;
                printMatrix();
                System.out.println("GAME OVER!");
            }

            else if (tries == word.length()) {
                isOver = true;
                System.out.println("You won! Huuu!");
            }
        }

    }
}
