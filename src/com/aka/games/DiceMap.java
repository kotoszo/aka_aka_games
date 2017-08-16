package com.aka.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DiceMap {

    int p1Fields, p1Units, p2Fields, p2Units;
    Character[][] matrix;

    public DiceMap() {

        this.p1Fields = p1Fields;
        this.p1Units = p1Units;
        this.p2Fields = p2Fields;
        this.p2Units = p2Units;
        this.matrix = initMatrix();

        int[] startSnail = startingPoint("A");
        snail(startSnail);

        int[] startDog = startingPoint("B");
        dog(startDog);

        int[] startFish = startingPoint("C");
        fish(startFish);

        int[] startGhostShark = startingPoint("D");
        ghostShark(startGhostShark);
        drawMatrix();

    }

    public Character[][] initMatrix() {
        Character[][] matrix= new Character[14][144];
        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 144; x++) {
                matrix[y][x] = ' ';
            }
        }
        return matrix;
    }
    
    public void drawMatrix(){
        for (int x=0; x < 14; x++) {
            for (int y=0; y < 144; y++) {
                System.out.print(matrix[x][y]);
            }
            System.out.println();
        }

    }

    private int[] startingPoint(String fieldName) {
        int[] startingCoordinates = new int[2];
        switch (fieldName){
            case "A":
                startingCoordinates[0] = 3;
                startingCoordinates[1] = 4;
                break;
            case "B":
                // DOG
                startingCoordinates[0] = 1;
                startingCoordinates[1] = 16;
                break;
            case "C":
                // FISH
                startingCoordinates[0] = 0;
                startingCoordinates[1] = 25;
                break;
            case "D":
                // ghostShark
                startingCoordinates[0] = 0;
                startingCoordinates[1] = 35;
                break;
            case "E":
                break;
            case "F":
                break;
            case "G":
                break;
            case "H":
                break;
            case "I":
                break;
            case "J":
                break;
            case "K":
                break;
        }
        return startingCoordinates;
    }

    private void snail(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        matrix[y][x] = '_';
        matrix[y][x+1] = '_';
        matrix[y][x+2] = '\\';
        matrix[y][x+3] = '_';
        matrix[y][x+4] = '/';
        matrix[y][x+5] = '_';
        matrix[y][x+6] = '_';
        matrix[y][x+7] = '/';
        matrix[y-1][x+2] = '/';
        matrix[y-2][x+3] = '_';
        matrix[y-1][x+4] = '\\';
        matrix[y-1][x+8] = '/';
        matrix[y-2][x+8] = '@';
        matrix[y-2][x+9] = '@';
    }

    private void chicken(int[] startingCoordinates){
        int y = startingCoordinates[0]+3;
        int x = startingCoordinates[1];
        matrix[y][x+1] = '/';
        matrix[y][x+2] = '\\';
        matrix[y-1][x] = '(';
        matrix[y-1][x+3] = ')';
        matrix[y-2][x+2] = '|';
        matrix[y-3][x+1] = '(';
        matrix[y-3][x+3] = ')';
        matrix[y-3][x+4] = '>';
        matrix[y-4][x+2] = '_';
    }

    private void wizard(int[] startingCoordinates){
        int y = startingCoordinates[0]+3;
        int x = startingCoordinates[1];
        matrix[y][x+1] = '/';
        matrix[y][x+2] = '\\';
        matrix[y][x+4] = '|';
        matrix[y-1][x+1] = '[';
        matrix[y-1][x+2] = ']';
        matrix[y-1][x+3] = '/';
        matrix[y-1][x+4] = '|';
        matrix[y-2][x+1] = 'O';
        matrix[y-2][x+2] = 'O';
        matrix[y-2][x+4] = '|';
        matrix[y-3][x+1] = '/';
        matrix[y-3][x+2] = '\\';
    }

    private void rabbit(int[] startingCoordinates){
        int y = startingCoordinates[0]+3;
        int x = startingCoordinates[1];
        matrix[y][x+1] = 'o';
        matrix[y][x+2] = '_';
        matrix[y][x+3] = '(';
        matrix[y][x+4] = '"';
        matrix[y][x+5] = ')';
        matrix[y][x+6] = '(';
        matrix[y][x+7] = '"';
        matrix[y][x+8] = ')';
        matrix[y-1][x+1] = '(';
        matrix[y-1][x+2] = '-';
        matrix[y-1][x+3] = '.';
        matrix[y-1][x+4] = '-';
        matrix[y-1][x+5] = ')';
        matrix[y-2][x+1] = '(';
        matrix[y-2][x+2] = '\\';
        matrix[y-2][x+3] = '(';
        matrix[y-2][x+4] = '\\';
    }

    private void pig(int[] startingCoordinates){
        int y = startingCoordinates[0]+3;
        int x = startingCoordinates[0];
        matrix[y][x+2] = '/';
        matrix[y][x+3] = '\\';
        matrix[y][x+4] = '/';
        matrix[y][x+5] = '\\';
        matrix[y-1][x+1] = '@';
        matrix[y-1][x+2] = '\\';
        matrix[y-1][x+3] = '_';
        matrix[y-1][x+5] = '/';
        matrix[y-1][x+6] = '\\';
        matrix[y-1][x+7] = '_';
        matrix[y-1][x+8] = 'u';
        matrix[y-1][x+9] = '/';
        matrix[y-2][x+2] = '/';
        matrix[y-2][x+5] = '\\';
        matrix[y-2][x+6] = '/';
        matrix[y-2][x+7] = 'ʌ';
        matrix[y-2][x+8] = 'ʌ';
        matrix[y-2][x+9] = '\\';
        matrix[y-3][x+3] = '_';
        matrix[y-3][x+4] = '_';
        matrix[y-3][x+7] = '_';
        matrix[y-3][x+8] = '_';

    }


    private void man(int[] startingCoordinates){
        int y = startingCoordinates[0]+3;
        int x = startingCoordinates[1];
        matrix[y][x+1] = '/';
        matrix[y][x+3] = '\\';
        matrix[y-1][x+2] = '|';
        matrix[y-2][x+1] = '\\';
        matrix[y-2][x+2] = 'O';
        matrix[y-2][x+3] = '/';

    }

    private void woman(int[] startingCoordinates){
        int y = startingCoordinates[0]+3;
        int x = startingCoordinates[1];
        matrix[y][x+1] = '/';
        matrix[y][x+2] = '_';
        matrix[y][x+3] = '\\';
        matrix[y-1][x+2] = '|';
        matrix[y-2][x+1] = '\\';
        matrix[y-2][x+2] = 'O';
        matrix[y-2][x+3] = '/';

    }

    private void snake(int[] startingCoordinates) {
        int y = startingCoordinates[0] + 3;
        int x = startingCoordinates[0];
        matrix[y][x+1] = '\\';
        matrix[y][x+2] = '/';
        matrix[y][x+4] = '\\';
        matrix[y][x+5] = '_';
        matrix[y][x+6] = '/';
        matrix[y][x+8] = '\\';
        matrix[y][x+9] = '_';
        matrix[y][x+10] = '_';
        matrix[y][x+11] = '/';
        matrix[y-1][x] = '\\';
        matrix[y-1][x+3] = '_';
        matrix[y-1][x+7] = '_';
        matrix[y-1][x+8] = '/';
        matrix[y-1][x+9] = 'u';
        matrix[y-1][x+10] = 'u';
        matrix[y-1][x+11] = '\\';
        matrix[y-2][x+9] = '_';
        matrix[y-2][x+10] = '_';

    }

    private void giraffe(int[] startingCoordinates) {
        int y = startingCoordinates[0] + 5;
        int x = startingCoordinates[0];
        matrix[y][x] = '/';
        matrix[y][x+1] = '\\';
        matrix[y][x+2] = '/';
        matrix[y][x+3] = '\\';
        matrix[y-1][x] = '\\';
        matrix[y-1][x+1] = '_';
        matrix[y-1][x+2] = '_';
        matrix[y-1][x+3] = '/';
        matrix[y-2][x+4] = '/';
        matrix[y-3][x+4] = '\\';
        matrix[y-3][x+5] = '_';
        matrix[y-3][x+6] = '_';
        matrix[y-3][x+7] = '/';
        matrix[y-4][x+4] = '/';
        matrix[y-4][x+5] = 'ʌ';
        matrix[y-4][x+6] = 'ʌ';
        matrix[y-4][x+7] = '\\';
        matrix[y-5][x+5] = '_';
        matrix[y-5][x+6] = '_';
    }

    private void dog(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        matrix[y+1][x] = '\\';
        matrix[y+2][x] = '/';
        matrix[y+2][x+1] = '\\';
        matrix[y+2][x+2] = '/';
        matrix[y+2][x+3] = '\\';
        matrix[y+1][x+1] = '_';
        matrix[y+1][x+2] = '_';
        matrix[y+1][x+3] = '\\';
        matrix[y+1][x+4] = '_';
        matrix[y+1][x+5] = '_';
        matrix[y+1][x+6] = '/';
        matrix[y][x+6] = '\\';
        matrix[y-1][x+5] = '_';
        matrix[y-1][x+4] = '_';
        matrix[y][x+3] = '/';
        matrix[y][x+4] = 'ʌ';
        matrix[y][x+5] = 'ʌ';
    }

    private void fish(int [] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        matrix[y+1][x] = '\\';
        matrix[y+1][x+1] = '\\';
        matrix[y+2][x] = '/';
        matrix[y+2][x+1] = '/';
        matrix[y+1][x+2] = '_';
        matrix[y+1][x+3] = '_';
        matrix[y+1][x+4] = '/';
        matrix[y+2][x+4] = '\\';
        matrix[y][x+5] = '_';
        matrix[y][x+6] = '_';
        matrix[y][x+7] = '_';
        matrix[y+2][x+5] = '_';
        matrix[y+2][x+6] = '_';
        matrix[y+2][x+7] = '_';
        matrix[y+1][x+8] = '\\';
        matrix[y+2][x+8] = '/';
        matrix[y+1][x+6] = '∩';
        matrix[y+1][x+7] = '∩';
    }

    private void ghostShark(int[] startingCoordinates){
        int y = startingCoordinates[0];
        int x = startingCoordinates[1];
        matrix[y][x+3] = '_';
        matrix[y][x+4] = '_';
        matrix[y][x+5] = '_';
        matrix[y][x+6] = '_';
        matrix[y+1][x+2] = '/';
        matrix[y+2][x+1] = '/';
        matrix[y+2][x+2] = 'o';
        matrix[y+2][x+5] = 'o';
        matrix[y+3][x+1] = '|';
        matrix[y+4][x+1] = '|';
        matrix[y+1][x+7] = '\\';
        matrix[y+2][x+8] = '\\';
        matrix[y+3][x+8] = '|';
        matrix[y+4][x+8] = '|';
        matrix[y+3][x+3] = '/';
        matrix[y+4][x+2] = '/';
        matrix[y+4][x+5] = '\\';
        matrix[y+3][x+4] = '\\';
        matrix[y+4][x+3] = '/';
        matrix[y+4][x+4] = '\\';
    }
}
