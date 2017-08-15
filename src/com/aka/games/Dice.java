package com.aka.games;

import java.util.*;


public class Dice {
        private  List<String> abc = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"));
        private HashMap<String, Integer> p1Fields = new HashMap<>();
        private HashMap<String, Integer> p2Fields = new HashMap<>();
        private int fieldNumber;
        private int p1Units;
        private int p2Units;
        private int attacker;
        private int defender;
        private int attackerDices;
        private int defenderDices;
        private int attackerLoss = 0;
        private int defenderLoss = 0;
        private Random random = new Random();
        private ArrayList<Integer> attackerDice = new ArrayList<>();
        private ArrayList<Integer> defenderDice = new ArrayList<>();
        private int player = 1;

    public Dice() {
    }

    public void game() {
        String attacker;
        String defender;

        InputHandler dice = new InputHandler();
        fieldNumber = dice.diceInt("How many fields do you have: ");
        p1Units = dice.diceInt("How many units do you have: ");
        p2Units = p1Units;

        p1Fields = initFields(p1Fields, fieldNumber);
        System.out.println(p1Fields);
        p2Fields = initFields(p2Fields, fieldNumber);
        System.out.println(p2Fields);
        while (p1Units > 0 && p2Units > 0) {
            if (player == 1) {
                System.out.println("\nPlayer 1 turn!\n");
                attacker = dice.diceStr("Attacker field: ").toUpperCase();
                defender = dice.diceStr("Defender field: ").toUpperCase();
                int attackUnits = p1Fields.get(attacker);
                int defendUnits = p2Fields.get(defender);
                if (attackUnits <= 5) {
                    attackerDices = 1;
                } else if (attackUnits <= 10) {
                    attackerDices = 2;
                } else {
                    attackerDices = 3;
                }
                if (defendUnits <= 5) {
                    defenderDices = 1;
                } else {
                    defenderDices = 2;
                }
                attackerDice = diceList(attackerDices, attackerDice);
                defenderDice = diceList(defenderDices, defenderDice);

                System.out.println("\nDice:");
                System.out.print("\tAttacker: ");
                printResult(attackerDice);
                System.out.print("\tDefender: ");
                printResult(defenderDice);
                System.out.println("\nOutcome: ");
                countLoss();
                System.out.printf("\tAttacker: Lost %d unit\n", attackerLoss);
                System.out.printf("\tDefender: Lost %d unit\n", defenderLoss);

                p1Fields.put(attacker, (p1Fields.get(attacker) - attackerLoss));
                p1Units -= attackerLoss;
                p2Fields.put(defender, (p2Fields.get(defender) - defenderLoss));
                p2Units -= defenderLoss;
                attackerLoss = 0;
                defenderLoss = 0;


                player = 2;
            } else {
                System.out.println("\nPlayer 2 turn!\n");
                attacker = dice.diceStr("Attacker field: ").toUpperCase();
                defender = dice.diceStr("Defender field: ").toUpperCase();
                int attackUnits = p2Fields.get(attacker);
                int defendUnits = p1Fields.get(defender);
                if (attackUnits <= 5) {
                    attackerDices = 1;
                } else if (attackUnits <= 10) {
                    attackerDices = 2;
                } else {
                    attackerDices = 3;
                }
                if (defendUnits <= 5) {
                    defenderDices = 1;
                } else {
                    defenderDices = 2;
                }
                attackerDice = diceList(attackerDices, attackerDice);
                defenderDice = diceList(defenderDices, defenderDice);

                System.out.println("\nDice:");
                System.out.print("\tAttacker: ");
                printResult(attackerDice);
                System.out.print("\tDefender: ");
                printResult(defenderDice);
                System.out.println("\nOutcome: ");
                countLoss();
                System.out.printf("\tAttacker: Lost %d unit\n", attackerLoss);
                System.out.printf("\tDefender: Lost %d unit\n", defenderLoss);

                p2Fields.put(attacker, (p2Fields.get(attacker) - attackerLoss));
                p2Units -= attackerLoss;
                p1Fields.put(defender, (p1Fields.get(defender) - defenderLoss));
                p1Units -= defenderLoss;
                attackerLoss = 0;
                defenderLoss = 0;

                player = 1;
            }
            System.out.println("\nPlayer 1 fields:");
            System.out.println(p1Fields);
            System.out.println("\nPlayer 2 fields:");
            System.out.println(p2Fields);
        }
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
        if (attackerDice.size() < defenderDice.size()) {
            minLength = attackerDice.size();
        } else {
            minLength = defenderDice.size();
        }

        for (int i = 0; i < minLength; i++) {
            if (attackerDice.get(i) <= defenderDice.get(i)) {
                attackerLoss += 1;
            } else {
                defenderLoss += 1;
            }
        }
    }

    public HashMap<String, Integer> initFields (HashMap playerMap, int fieldNumber) {
        InputHandler map = new InputHandler();
        for (int i = 0; i < fieldNumber; i++) {
            playerMap.put((abc.get(i)), map.diceInt(String.format("Number of units on field \"%s\": ", abc.get(i))));
        }
        return playerMap;
    }
}

