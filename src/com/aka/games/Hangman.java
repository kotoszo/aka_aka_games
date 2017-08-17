package com.aka.games;

import java.io.*;
import java.util.*;

public class Hangman {

    private String word;
    private Character[] hiddenWord;
    private Character[][] matrix;

    public Hangman() {

        String[] wordList = getFile();
        this.word = getWord(wordList);
        this.hiddenWord = hideWord(word);
        this.matrix = death();
    }

    private String[] getFile() {

        String[] wordsList = new String[36];
        int counter = 0;
        InputStream fileStream = getClass().getResourceAsStream("words.csv");
        BufferedReader fileBuffered = new BufferedReader(new InputStreamReader(fileStream));
        try {
            boolean isEnd = false;
            while (!isEnd) {

                wordsList[counter] = fileBuffered.readLine();
                if (wordsList[counter] == null){
                    isEnd = true;
                }
                counter++;
            }
        } catch (IOException e) {
            System.out.println("Cant read the file");
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
        int winLose = 0;
        ArrayList<Character> usedWrongChars = new ArrayList<>();
        ArrayList<Character> usedCorrectChars = new ArrayList<>();
        while (!isOver) {
            boolean isContain = false;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            printMatrix();
            System.out.println("\n"+Arrays.toString(hiddenWord)+"\n");
            System.out.println("Your already used characters: " + usedWrongChars);
            System.out.println("Give me your guess!");
            Scanner hangManInput = new Scanner(System.in);
            char guess = hangManInput.next().charAt(0);
            if (Character.isDigit(guess)) {
                System.out.println("Digit? What were you thinking?");
            } else if (Character.isAlphabetic(guess)){
                if (!usedWrongChars.contains(guess) && !usedCorrectChars.contains(guess)) {
                    for (int i=0; i < word.length(); i++) {
                        if (word.charAt(i) == guess) {
                            hiddenWord[i] = guess;
                            isContain = true;
                            usedCorrectChars.add(guess);
                        }
                    }
                    if (!isContain) {
                        usedWrongChars.add(guess);
                        isContain = false;
                        mistakes++;
                        wayToHell(mistakes);
                    }
                }
            }
            if (mistakes == 10 || usedCorrectChars.size() == word.length()) {
                isOver = true;
                if (mistakes == 10) {
                    winLose = 2;
                } else {
                    winLose = 1;
                }
                gameOver(winLose);
            }
        }

    }

    private void gameOver(int winLose){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        printMatrix();
        switch (winLose){
            case 1:
                System.out.println("\nYou won! Huuu!\n");
                break;
            case 2:
                System.out.println("\nGAME OVER!\n");
                break;
        }
        System.out.println("The word was: " + word + "\n");
    }
}
