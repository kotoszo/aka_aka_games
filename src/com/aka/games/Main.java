package com.aka.games;

import java.util.HashMap;

public class Main {


    public static void main(String[] args) {
        String[] gameList = {"Hangman", "Dice for risk", "GTA", "Quit"};
        //Hangman a = new Hangman();
        //System.out.println(a.word);

        printMain(gameList);

        InputHandler game = new InputHandler();
        int option = game.mainMenu(gameList.length);

        switch (option) {
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
        }


    }

    private static void printMain(String[] gameList) {
        int index = 1;

        HashMap<Integer, String> gameLib = new HashMap<>();

        for (String game : gameList) {
            gameLib.put(index++, game);
        }

        for (int i=1; i <= gameLib.size(); i++){
            if (i == 4) {
                System.out.printf("\n[%d]     %s\n", 0, gameLib.get(i));
            } else {
                System.out.printf("[%d]     %s\n", i, gameLib.get(i));
            }

        }
    }
}