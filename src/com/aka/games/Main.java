package com.aka.games;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        DiceMap a = new DiceMap();

        /*String[] gameList = {"Hangman", "Dice for risk", "GTA"};
        //Hangman a = new Hangman();
        //System.out.println(a.word);

        printMain(gameList);
        System.out.print("Enter a number to play or '0' to Quit: ");
        InputHandler game = new InputHandler();
        int option = game.mainMenu(gameList.length);


        switch (option) {
            case 0:
                System.exit(0);
                break;
            case 1:
                Hangman start = new Hangman();
                start.appRun();
                break;
            case 2:
                Dice startDice = new Dice();
                startDice.game();
                break;
            case 3:
                System.out.println(3);
                break;
        }*/


    }

    private static void printMain(String[] gameList) {
        int index = 1;

        HashMap<Integer, String> gameLib = new HashMap<>();

        for (String game : gameList) {
            gameLib.put(index++, game);
        }

        for (int i=1; i <= gameLib.size(); i++) {
            System.out.printf("[%d]     %s\n", i, gameLib.get(i));
        }
        System.out.printf("\n[%d]     %s\n\n", 0, "Quit");
    }
}