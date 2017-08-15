package com.aka.games;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;


public class Dice {
        int attacker;
        int defender;
        private Random random = new Random();
        private ArrayList<Integer> attackerDice = new ArrayList<>();
        private ArrayList<Integer> defenderDice = new ArrayList<>();

    public Dice(int attacker, int defender) {
        this.attacker = attacker;
        this.defender = defender;

    }

    public void game() {

        attackerDice = diceList(attacker, attackerDice);
        defenderDice = diceList(defender, defenderDice);

    }

    public ArrayList diceList (int units, ArrayList diceList ) {
        for (int i=0; i < units; i++) {

            int randomNr = random.nextInt(6) + 1;
            diceList.add(randomNr);
        }
        Collections.sort(diceList);
        Collections.reverse(diceList);

        System.out.println(diceList);
        return diceList;
    }


        // System.out.println("Dice");

}

