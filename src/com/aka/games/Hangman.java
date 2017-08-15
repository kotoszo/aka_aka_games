package com.aka.games;

import java.io.*;
import java.net.URL;
import java.util.*;
import static java.lang.Math.toIntExact;

public class Hangman {

    String word;
    Character[] hiddenword;

    public Hangman() {

        String[] wordList = getFile();
        this.word = getWord(wordList);
        this.hiddenword = hideWord(word);
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

    public void appRun() {
        boolean isOver = false;
        int mistakes = 0;
        int tries = 0;
        ArrayList<Character> usedChars = new ArrayList<>();
        while (!isOver) {
            boolean isContain = false;
            System.out.println(Arrays.toString(hiddenword));
            System.out.println("Give me your shot bro!");
            Scanner hangManInput = new Scanner(System.in);
            char guess = hangManInput.next().charAt(0);
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
                }
            }
            System.out.println("Your already used characters: " + usedChars);
            if (mistakes == 10) {
                isOver = true;
                System.out.println("Fucked up!");
            }
            else if (tries == word.length()) {
                isOver = true;
                System.out.println("You won!Huuu!");
            }
        }

    }
}
