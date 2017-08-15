package com.aka.games;

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

    public int dice(String message) {
        System.out.print(message);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
