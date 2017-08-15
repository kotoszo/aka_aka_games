package com.aka.games;

import java.io.*;
import java.net.URL;
import java.util.*;
import static java.lang.Math.toIntExact;

public class Hangman {

    String word;
    String[] hiddenword;

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

    private String[] hideWord(String word) {
        String[] hiddenWord = new String[word.length()];

        for (int i=0; i < word.length(); i++){
            hiddenWord[i] = "_";
        }
        return hiddenWord;
    }
}
