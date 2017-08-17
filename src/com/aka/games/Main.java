package com.aka.games;

import javafx.beans.binding.ObjectExpression;

import javax.swing.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        boolean isEnough = false;
        while (!isEnough) {

            String[] gameList = {"Hangman", "Dice for risk", "GTA"};

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
                    GTA GTAStart = new GTA();
                    GTAStart.image();
                    GTAStart.setVisible(true);
                    GTAStart.pack();
                    GTAStart.setTitle("BUSTED!");
                    break;
            }
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
        System.out.printf("\n[%d]     %s\n\n", 0, "Quit");
    }
}