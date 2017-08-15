package com.aka.games;

import java.util.*;


public class Dice {
        private HashMap<String, Integer> p1Fields = new HashMap<>();
        private HashMap<String, Integer> p2Fields = new HashMap<>();
        private int p1Units = 20;
        private int p2Units = 20;
        private int attacker;
        private int defender;
        private int attackerLoss = 0;
        private int defenderLoss = 0;
        private Random random = new Random();
        private ArrayList<Integer> attackerDice = new ArrayList<>();
        private ArrayList<Integer> defenderDice = new ArrayList<>();

    public Dice(int attacker, int defender) {
        this.attacker = attacker;
        this.defender = defender;

    }

    public void game() {
        p1Fields = initFields(p1Fields);
        System.out.println(p1Fields);
        p2Fields = initFields(p2Fields);
        System.out.println(p2Fields);


        attackerDice = diceList(attacker, attackerDice);
        defenderDice = diceList(defender, defenderDice);
        System.out.println("\nDice:");
        System.out.print("\tAttacker: ");
        printResult(attackerDice);
        System.out.print("\tDefender: ");
        printResult(defenderDice);
        System.out.println("\nOutcome: ");
        countLoss();
        System.out.printf("\tAttacker: Lost %d unit\n", attackerLoss);
        System.out.printf("\tDefender: Lost %d unit\n", defenderLoss);
    }

    public ArrayList diceList (int units, ArrayList diceList ) {
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
    public HashMap<String, Integer> initFields (HashMap playerMap) {
        InputHandler map = new InputHandler();
        playerMap.put("a", map.dice("Number of units on field \"A\": "));
        playerMap.put("b", map.dice("Number of units on field \"B\": "));
        return playerMap;
    }

}

