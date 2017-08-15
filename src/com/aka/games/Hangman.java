package com.aka.games;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    String word;
    ArrayList<String> wordMap;

    public Hangman() {

        getFile();
        getWord();
        //this.word = getWord();
    }

    public void getFile() {
        int counter = 0;
        String b = new String();

        try {

            Scanner scanner = new Scanner(new File("/home/tanacs/java/aka_aka_games/src/com/aka/games/words.csv"));

            // here comes the iteration
            
            System.out.println(b);
            /*while (scanner.hasNext()) {
                String a = scanner.nextLine();
                System.out.println(a);
                wordMap.add(a);
            }*/
        } catch (FileNotFoundException e) {
            System.out.println("Damn dude");
        }
    }

    public String getWord(){

        Random random = new Random();
        int randomNr = random.nextInt(35);
        System.out.println(randomNr);
        System.out.println(wordMap.get(randomNr));
        return wordMap.get(randomNr);
    }
}
