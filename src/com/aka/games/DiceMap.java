package com.aka.games;

import java.util.*;

public class DiceMap {

    String[][] matrix;

    public DiceMap() {

        this.matrix = initMatrix();

    }

    public String[][] initMatrix() {
        String[][] matrix= new String[20][144];
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 144; x++) {
                matrix[y][x] = " ";
            }
        }
        return matrix;
    }
    
    public void drawMatrix(){
        for (int x=0; x < 20; x++) {
            for (int y=0; y < 144; y++) {
                System.out.print(matrix[x][y]);
            }
            System.out.println();
        }

    }

    public void manipulateMatrix(HashMap<String, Integer> playerMap, int playerNr){
        for (HashMap.Entry<String, Integer> entry: playerMap.entrySet()){
            startingPoint(entry.getKey(), entry.getValue(), playerNr);
            System.out.println(entry.getKey()+" "+ entry.getValue());
        }
    }

    private int[] startingPoint(String fieldName, int unitNumber, int playerNr) {
        int[] startingCoordinates = new int[3];
        int y = 0;
        switch (playerNr){
            case 1:
                y = 0;
                break;
            case 2:
                y = 11;
                break;
        }
        switch (fieldName){
            case "A":
                // SNAIL
                startingCoordinates[0] = 5+y;
                startingCoordinates[1] = 4;
                startingCoordinates[2] = unitNumber;
                snail(startingCoordinates);
                break;
            case "B":
                // DOG
                startingCoordinates[0] = 3+y;
                startingCoordinates[1] = 16;
                startingCoordinates[2] = unitNumber;
                dog(startingCoordinates);
                break;
            case "C":
                // FISH
                startingCoordinates[0] = 2+y;
                startingCoordinates[1] = 25;
                startingCoordinates[2] = unitNumber;
                fish(startingCoordinates);
                break;
            case "D":
                // GHOSTSHARK
                startingCoordinates[0] = 1+y;
                startingCoordinates[1] = 35;
                startingCoordinates[2] = unitNumber;
                ghostShark(startingCoordinates);
                break;
            case "E":
                // MAN
                startingCoordinates[0] = 5+y;
                startingCoordinates[1] = 46;
                startingCoordinates[2] = unitNumber;
                man(startingCoordinates);
                break;
            case "F":
                // WOMAN
                startingCoordinates[0] = 2+y;
                startingCoordinates[1] = 52;
                startingCoordinates[2] = unitNumber;
                woman(startingCoordinates);
                break;
            case "G":
                // GIRAFFE
                startingCoordinates[0] = 5+y;
                startingCoordinates[1] = 60;
                startingCoordinates[2] = unitNumber;
                giraffe(startingCoordinates);
                break;
            case "H":
                // PIG
                startingCoordinates[0] = 5+y;
                startingCoordinates[1] = 70;
                startingCoordinates[2] = unitNumber;
                pig(startingCoordinates);
                break;
            case "I":
                // WIZARD
                startingCoordinates[0] = 5+y;
                startingCoordinates[1] = 82;
                startingCoordinates[2] = unitNumber;
                wizard(startingCoordinates);
                break;
            case "J":
                // CHICKEN
                startingCoordinates[0] = 5+y;
                startingCoordinates[1] = 92;
                startingCoordinates[2] = unitNumber;
                chicken(startingCoordinates);
                break;
            case "K":
                // RABBIT
                startingCoordinates[0] = 5+y;
                startingCoordinates[1] = 100;
                startingCoordinates[2] = unitNumber;
                rabbit(startingCoordinates);
                break;
            case "L":
                // SNAKE
                startingCoordinates[0] = 5+y;
                startingCoordinates[1] = 110;
                startingCoordinates[2] = unitNumber;
                snake(startingCoordinates);
                break;
        }

        return startingCoordinates;
    }

    private void snail(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x] = "_";
        matrix[y][x+1] = "_";
        matrix[y][x+2] = "\\";
        matrix[y][x+3] = "_";
        matrix[y][x+4] = "/";
        matrix[y][x+5] = "_";
        matrix[y][x+6] = "_";
        matrix[y][x+7] = "/";
        matrix[y-1][x+2] = "/";
        matrix[y-2][x+3] = "_";
        matrix[y-1][x+4] = "\\";
        matrix[y-1][x+8] = "/";
        matrix[y-2][x+8] = "@";
        matrix[y-2][x+9] = "@";
        matrix[y+2][x+4] = Integer.toString(health);
    }

    private void chicken(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x+1] = "/";
        matrix[y][x+2] = "\\";
        matrix[y-1][x] = "(";
        matrix[y-1][x+3] = ")";
        matrix[y-2][x+2] = "|";
        matrix[y-3][x+1] = "(";
        matrix[y-3][x+3] = ")";
        matrix[y-3][x+4] = ">";
        matrix[y-4][x+2] = "_";
        matrix[y+2][x+2] = Integer.toString(health);
    }

    private void wizard(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x+1] = "/";
        matrix[y][x+2] = "\\";
        matrix[y][x+4] = "|";
        matrix[y-1][x+1] = "[";
        matrix[y-1][x+2] = "]";
        matrix[y-1][x+3] = "/";
        matrix[y-1][x+4] = "|";
        matrix[y-2][x+1] = "O";
        matrix[y-2][x+2] = "O";
        matrix[y-2][x+4] = "|";
        matrix[y-3][x+1] = "/";
        matrix[y-3][x+2] = "\\";
        matrix[y+2][x+2] = Integer.toString(health);
    }

    private void rabbit(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x+1] = "o";
        matrix[y][x+2] = "_";
        matrix[y][x+3] = "(";
        matrix[y][x+4] = "\"";
        matrix[y][x+5] = ")";
        matrix[y][x+6] = "(";
        matrix[y][x+7] = "\"";
        matrix[y][x+8] = ")";
        matrix[y-1][x+1] = "(";
        matrix[y-1][x+2] = "-";
        matrix[y-1][x+3] = ".";
        matrix[y-1][x+4] = "-";
        matrix[y-1][x+5] = ")";
        matrix[y-2][x+1] = "(";
        matrix[y-2][x+2] = "\\";
        matrix[y-2][x+3] = "(";
        matrix[y-2][x+4] = "\\";
        matrix[y+2][x+4] = Integer.toString(health);
    }

    private void pig(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x+2] = "/";
        matrix[y][x+3] = "\\";
        matrix[y][x+4] = "/";
        matrix[y][x+5] = "\\";
        matrix[y-1][x+1] = "@";
        matrix[y-1][x+2] = "\\";
        matrix[y-1][x+3] = "_";
        matrix[y-1][x+5] = "/";
        matrix[y-1][x+6] = "\\";
        matrix[y-1][x+7] = "_";
        matrix[y-1][x+8] = "u";
        matrix[y-1][x+9] = "/";
        matrix[y-2][x+2] = "/";
        matrix[y-2][x+5] = "\\";
        matrix[y-2][x+6] = "/";
        matrix[y-2][x+7] = "ʌ";
        matrix[y-2][x+8] = "ʌ";
        matrix[y-2][x+9] = "\\";
        matrix[y-3][x+3] = "_";
        matrix[y-3][x+4] = "_";
        matrix[y-3][x+7] = "_";
        matrix[y-3][x+8] = "_";
        matrix[y+2][x+4] = Integer.toString(health);

    }


    private void man(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x+1] = "/";
        matrix[y][x+3] = "\\";
        matrix[y-1][x+2] = "|";
        matrix[y-2][x+1] = "\\";
        matrix[y-2][x+2] = "O";
        matrix[y-2][x+3] = "/";
        matrix[y+2][x+2] = Integer.toString(health);

    }

    private void woman(int[] startingCoordinates){
        int y = startingCoordinates[0]+3;
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x+1] = "/";
        matrix[y][x+2] = "_";
        matrix[y][x+3] = "\\";
        matrix[y-1][x+2] = "|";
        matrix[y-2][x+1] = "\\";
        matrix[y-2][x+2] = "O";
        matrix[y-2][x+3] = "/";
        matrix[y+2][x+2] = Integer.toString(health);

    }

    private void snake(int[] startingCoordinates) {
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x+1] = "\\";
        matrix[y][x+2] = "/";
        matrix[y][x+4] = "\\";
        matrix[y][x+5] = "_";
        matrix[y][x+6] = "/";
        matrix[y][x+8] = "\\";
        matrix[y][x+9] = "_";
        matrix[y][x+10] = "_";
        matrix[y][x+11] = "/";
        matrix[y-1][x] = "\\";
        matrix[y-1][x+3] = "_";
        matrix[y-1][x+7] = "_";
        matrix[y-1][x+8] = "/";
        matrix[y-1][x+9] = "u";
        matrix[y-1][x+10] = "u";
        matrix[y-1][x+11] = "\\";
        matrix[y-2][x+9] = "_";
        matrix[y-2][x+10] = "_";
        matrix[y+2][x+4] = Integer.toString(health);

    }

    private void giraffe(int[] startingCoordinates) {
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x] = "/";
        matrix[y][x+1] = "\\";
        matrix[y][x+2] = "/";
        matrix[y][x+3] = "\\";
        matrix[y-1][x] = "\\";
        matrix[y-1][x+1] = "_";
        matrix[y-1][x+2] = "_";
        matrix[y-1][x+3] = "/";
        matrix[y-2][x+4] = "/";
        matrix[y-3][x+4] = "\\";
        matrix[y-3][x+5] = "_";
        matrix[y-3][x+6] = "_";
        matrix[y-3][x+7] = "/";
        matrix[y-4][x+4] = "/";
        matrix[y-4][x+5] = "ʌ";
        matrix[y-4][x+6] = "ʌ";
        matrix[y-4][x+7] = "\\";
        matrix[y-5][x+5] = "_";
        matrix[y-5][x+6] = "_";
        matrix[y+2][x+2] = Integer.toString(health);
    }

    private void dog(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y+1][x] = "\\";
        matrix[y+2][x] = "/";
        matrix[y+2][x+1] = "\\";
        matrix[y+2][x+2] = "/";
        matrix[y+2][x+3] = "\\";
        matrix[y+1][x+1] = "_";
        matrix[y+1][x+2] = "_";
        matrix[y+1][x+3] = "\\";
        matrix[y+1][x+4] = "_";
        matrix[y+1][x+5] = "_";
        matrix[y+1][x+6] = "/";
        matrix[y][x+6] = "\\";
        matrix[y-1][x+5] = "_";
        matrix[y-1][x+4] = "_";
        matrix[y][x+3] = "/";
        matrix[y][x+4] = "ʌ";
        matrix[y][x+5] = "ʌ";
        matrix[y+4][x+2] = Integer.toString(health);
    }

    private void fish(int [] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y+1][x] = "\\";
        matrix[y+1][x+1] = "\\";
        matrix[y+2][x] = "/";
        matrix[y+2][x+1] = "/";
        matrix[y+1][x+2] = "_";
        matrix[y+1][x+3] = "_";
        matrix[y+1][x+4] = "/";
        matrix[y+2][x+4] = "\\";
        matrix[y][x+5] = "_";
        matrix[y][x+6] = "_";
        matrix[y][x+7] = "_";
        matrix[y+2][x+5] = "_";
        matrix[y+2][x+6] = "_";
        matrix[y+2][x+7] = "_";
        matrix[y+1][x+8] = "\\";
        matrix[y+2][x+8] = "/";
        matrix[y+1][x+6] = "∩";
        matrix[y+1][x+7] = "∩";
        matrix[y+5][x+4] = Integer.toString(health);
    }

    private void ghostShark(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        int health = startingCoordinates[2];
        matrix[y][x+3] = "_";
        matrix[y][x+4] = "_";
        matrix[y][x+5] = "_";
        matrix[y][x+6] = "_";
        matrix[y+1][x+2] = "/";
        matrix[y+2][x+1] = "/";
        matrix[y+2][x+2] = "o";
        matrix[y+2][x+5] = "o";
        matrix[y+3][x+1] = "|";
        matrix[y+4][x+1] = "|";
        matrix[y+1][x+7] = "\\";
        matrix[y+2][x+8] = "\\";
        matrix[y+3][x+8] = "|";
        matrix[y+4][x+8] = "|";
        matrix[y+3][x+3] = "/";
        matrix[y+4][x+2] = "/";
        matrix[y+4][x+5] = "\\";
        matrix[y+3][x+4] = "\\";
        matrix[y+4][x+3] = "/";
        matrix[y+4][x+4] = "\\";
        matrix[y+6][x+4] = Integer.toString(health);
    }
}
