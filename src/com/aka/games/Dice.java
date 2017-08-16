package com.aka.games;

import java.util.*;


public class Dice {
        private  List<String> worldMap = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"));
        private HashMap<String, Integer> playerOneFields = new HashMap<>();
        private HashMap<String, Integer> playerTwoFields = new HashMap<>();
        private int attackerLoss = 0;
        private int defenderLoss = 0;
        private Random random = new Random();
        private ArrayList<Integer> attackerDiceList = new ArrayList<>();
        private ArrayList<Integer> defenderDiceList = new ArrayList<>();
        private int player = 1;

    public Dice() {
    }

    public void game() {
        int attackerUnits;
        int defenderUnits;
        int fieldNumber;
        int totalUnits;
        int playerOneUnits;
        int playerTwoUnits;
        int attackerDices;
        int defenderDices;
        String attackerField;
        String defenderField;

        InputHandler dice = new InputHandler();
        fieldNumber = dice.diceInt("How many fields do you have: ");
        totalUnits = dice.diceInt("How many units do you have: ");
        playerOneUnits = totalUnits;
        playerTwoUnits = totalUnits;

        playerOneFields = initFields(playerOneFields, fieldNumber);
        System.out.println(playerOneFields);
        playerTwoFields = initFields(playerTwoFields, fieldNumber);
        System.out.println(playerTwoFields);
        while (playerOneUnits > 0 && playerTwoUnits > 0) {
            if (player == 1) {
                System.out.println("\nPlayer 1 turn!\n");
                attackerField = dice.diceStr("Attacker field: ").toUpperCase();
                defenderField = dice.diceStr("Defender field: ").toUpperCase();
                attackerUnits = playerOneFields.get(attackerField);
                defenderUnits = playerTwoFields.get(defenderField);
                attackerDices = getDices(attackerUnits, "attackerField");
                defenderDices = getDices(defenderUnits, "defenderField");

                attackerDiceList = diceList(attackerDices, attackerDiceList);
                defenderDiceList = diceList(defenderDices, defenderDiceList);

                System.out.println("\nDice:");
                System.out.print("\tAttacker: ");
                printResult(attackerDiceList);
                System.out.print("\tDefender: ");
                printResult(defenderDiceList);
                System.out.println("\nOutcome: ");
                countLoss();
                System.out.printf("\tAttacker: Lost %d unit\n", attackerLoss);
                System.out.printf("\tDefender: Lost %d unit\n", defenderLoss);

                playerOneFields.put(attackerField, (playerOneFields.get(attackerField) - attackerLoss));
                playerOneUnits -= attackerLoss;
                playerTwoFields.put(defenderField, (playerTwoFields.get(defenderField) - defenderLoss));
                playerTwoUnits -= defenderLoss;
                attackerLoss = 0;
                defenderLoss = 0;


                player = 2;
            } else {
                System.out.println("\nPlayer 2 turn!\n");
                attackerField = dice.diceStr("Attacker field: ").toUpperCase();
                defenderField = dice.diceStr("Defender field: ").toUpperCase();
                attackerUnits = playerTwoFields.get(attackerField);
                defenderUnits = playerOneFields.get(defenderField);
                attackerDices = getDices(attackerUnits, "attackerField");
                defenderDices = getDices(defenderUnits, "defenderField");

                attackerDiceList = diceList(attackerDices, attackerDiceList);
                defenderDiceList = diceList(defenderDices, defenderDiceList);

                System.out.println("\nDice:");
                System.out.print("\tAttacker: ");
                printResult(attackerDiceList);
                System.out.print("\tDefender: ");
                printResult(defenderDiceList);
                System.out.println("\nOutcome: ");
                countLoss();
                System.out.printf("\tAttacker: Lost %d unit\n", attackerLoss);
                System.out.printf("\tDefender: Lost %d unit\n", defenderLoss);

                playerTwoFields.put(attackerField, (playerTwoFields.get(attackerField) - attackerLoss));
                playerTwoUnits -= attackerLoss;
                playerOneFields.put(defenderField, (playerOneFields.get(defenderField) - defenderLoss));
                playerOneUnits -= defenderLoss;
                attackerLoss = 0;
                defenderLoss = 0;

                player = 1;
            }
            System.out.println("\nPlayer 1 fields:");
            System.out.println(playerOneFields);
            System.out.println("\nPlayer 2 fields:");
            System.out.println(playerTwoFields);
        }
    }

    public int getDices(int units, String role) {
        int dices;
        if (role == "attacker") {
            if (units <= 5) {
                dices = 1;
            } else if (units <= 10) {
                dices = 2;
            } else {
                dices = 3;
            }
        } else {
            if (units <= 5) {
                dices = 1;
            } else {
                dices = 2;
            }
        }
        return dices;
    }

    public ArrayList diceList (int units, ArrayList diceList) {
        diceList.clear();
        for (int i=0; i < units; i++) {
            int randomNr = random.nextInt(6) + 1;
            diceList.add(randomNr);
        }
        Collections.sort(diceList);
        Collections.reverse(diceList);
        return diceList;
    }

    public void printResult(ArrayList diceList) {
        for (int i = 0; i < diceList.size(); i++) {
            if (i == diceList.size()-1) {
                System.out.printf("%d\n", diceList.get(i));
            } else {
                System.out.printf("%d-", diceList.get(i));
            }
        }
    }

    public void countLoss () {
        int minLength;
        if (attackerDiceList.size() < defenderDiceList.size()) {
            minLength = attackerDiceList.size();
        } else {
            minLength = defenderDiceList.size();
        }

        for (int i = 0; i < minLength; i++) {
            if (attackerDiceList.get(i) <= defenderDiceList.get(i)) {
                attackerLoss += 1;
            } else {
                defenderLoss += 1;
            }
        }
    }

    public HashMap<String, Integer> initFields (HashMap playerMap, int fieldNumber) {
        InputHandler map = new InputHandler();
        for (int i = 0; i < fieldNumber; i++) {
            playerMap.put((worldMap.get(i)), map.diceInt(String.format("Number of units on field \"%s\": ", worldMap.get(i))));
        }
        return playerMap;
    }
}

