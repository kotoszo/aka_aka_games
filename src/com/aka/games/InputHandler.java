package com.aka.games;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    Integer userChoice;

    public int mainMenu(int listLength){

        boolean isEnd = false;


        while (!isEnd) {
            Scanner userInput = new Scanner(System.in);

            try {

                this.userChoice = Integer.parseInt(userInput.nextLine());
                if (0 <= userChoice && userChoice <= 3) {
                    isEnd = true;
                } else {
                    System.out.println("Error babe, between 0 and 3 please");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error babe, not really a valid number...");
            }
        }

        return userChoice;
    }

    public int hangMan(int userInput){

        return userInput;
    }

    public int diceInt(String message) {
        boolean isInt = false;
        int inputInt = 0;
        while (!isInt) {
            System.out.print(message);
            Scanner input = new Scanner(System.in);
            try {
                inputInt = input.nextInt();
                isInt = true;
            } catch (InputMismatchException e) {
                System.out.println("Not a valid number!");
            }
        }
        return inputInt;
    }

    public String diceStr(String message) {
        boolean isString = false;
        int intTemp = 0;
        String inputStr = "";
        while (!isString) {
            System.out.print(message);
            Scanner input = new Scanner(System.in);
            try {
              intTemp = input.nextInt();
              System.out.println("Not a valid character!");
            } catch (InputMismatchException e) {
                inputStr = input.next();
                isString = true;
            }
        }
        return inputStr;
    }
}
