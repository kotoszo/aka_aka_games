package com.aka.games;

import java.util.HashMap;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String[] gameList = {"Hangman", "Dice for risk", "GTA"};
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
                Hangman start = new Hangman();
                start.getFile();
                System.out.println(1);
                break;
            case 2:
                Scanner input = new Scanner(System.in);
                System.out.println("How many units attack: ");
                int attacker = input.nextInt();
                System.out.println("How many units defend: ");
                int defender = input.nextInt();
                input.close();
                Dice startDice = new Dice(attacker, defender);
                startDice.game();
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

        for (int i=1; i <= gameLib.size(); i++) {
            System.out.printf("[%d]     %s\n", i, gameLib.get(i));
        }
        System.out.printf("\n[%d]     %s\n", 0, "Quit");
    }
}