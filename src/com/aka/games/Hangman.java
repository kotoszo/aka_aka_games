package com.aka.games;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Hangman {

    Object word;

    public Hangman() {

        ArrayList<String> wordList = getFile();
        this.word = getWord(wordList);
    }

    public ArrayList getFile() {
        ArrayList<String> wordsList = new ArrayList<>();


        URL url = getClass().getResource("words.csv");
        File file = new File(url.getFile());

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                wordsList.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Meh");
        }
        return wordsList;

    }

    public Object getWord(ArrayList wordList){

        Random random = new Random();
        int randomNr = random.nextInt(35);
        return wordList.get(randomNr);
    }
}
