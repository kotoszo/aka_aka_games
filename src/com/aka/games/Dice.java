package com.aka.games;

import java.util.*;


public class Dice {
    private  List<String> worldMap = new ArrayList<>(Arrays.asList(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L"));
    private  List<String> warriorMap = new ArrayList<>(Arrays.asList(
            "Snail",
            "Dog",
            "Fish",
            "GhostShark",
            "Man",
            "Woman",
            "Giraffe",
            "Pig",
            "Wizard",
            "Chicken",
            "Rabbit",
            "Snake"));
    private HashMap<String, Integer> playerOneFields = new HashMap<>();
    private HashMap<String, Integer> playerTwoFields = new HashMap<>();
    private int attackerLoss = 0;
    private int defenderLoss = 0;
    private Random random = new Random();
    private ArrayList<Integer> attackerDiceList = new ArrayList<>();
    private ArrayList<Integer> defenderDiceList = new ArrayList<>();
    private int player = 1;
    private int playerOneUnits;
    private int playerTwoUnits;
    private String attackerField = "";
    private String defenderField = "";
    private int attackerUnits =0;
    private int defenderUnits =0;
    private DiceMap draw;
    private InputHandler dice;

    public Dice() {
    }

    public void game() {
        this.dice = new InputHandler();
        this.draw = new DiceMap();
        int fieldNumber;
        int totalUnits;

        // Asking the field and the unit numbers from the users.
        fieldNumber = dice.diceInt("Number of fields for each player: ");
        if (fieldNumber <= 0) {
            System.out.println("It seems you don't want to play!");
            System.exit(0);
        }
        totalUnits = dice.diceInt("Total number of units for each player: ");
        if (totalUnits < fieldNumber) {
            System.out.println("It seems you don't want to play!");
            System.exit(0);
        }
        playerOneUnits = totalUnits;
        playerTwoUnits = totalUnits;

        // Places the units to the fields.
        System.out.print("\nPlayer 1 distribute units for the fields\n");
        playerOneFields = initFields(playerOneFields, fieldNumber, playerOneUnits);
        draw.manipulateMatrix(playerOneFields, 1);

        System.out.print("\nPlayer 2 distribute units for the fields\n");
        playerTwoFields = initFields(playerTwoFields, fieldNumber, playerTwoUnits);
        draw.manipulateMatrix(playerTwoFields, 2);


        // Running the game.
        while (playerOneUnits > 0 && playerTwoUnits > 0) {
            draw.drawMatrix();
            if (player == 1) {
                playerTurn(1);
                player = 2;
            } else {
                playerTurn(2);
                player = 1;
            }
        }
        draw.drawMatrix();
        System.out.println("GameOver");
    }

    private void playerTurn(int player) {
        int attackerDices;
        int defenderDices;

        System.out.printf("\n\nPlayer %d turn!\n", player);

        // Asking the attacker field and the defender field from the attacking player.
        if (player == 1) {
            getValidField(playerOneFields, playerTwoFields);
        } else {
            getValidField(playerTwoFields, playerOneFields);
        }

        // Counts the dices.
        attackerDices = getDices(attackerUnits, "attacker");
        defenderDices = getDices(defenderUnits, "defender");

        // Makes a list from the dices.
        attackerDiceList = diceList(attackerDices);
        defenderDiceList = diceList(defenderDices);

        // Prints out the result.
        System.out.println("\nDice:");
        System.out.print("\tAttacker: ");
        printResult(attackerDiceList);
        System.out.print("\tDefender: ");
        printResult(defenderDiceList);
        System.out.println("\nOutcome: ");

        // Counts the amount of the attacker and the defender losses, and prints it out.
        countLoss();
        System.out.printf("\tAttacker: Lost %d unit\n", attackerLoss);
        System.out.printf("\tDefender: Lost %d unit\n", defenderLoss);

        // Changes the units amount on the specific field, ant counts the total army.
        if (player == 1) {
            changeFieldValues(playerOneFields, playerTwoFields);
            playerOneUnits -= attackerLoss;
            playerTwoUnits -= defenderLoss;
        } else {
            changeFieldValues(playerTwoFields, playerOneFields);
            playerTwoUnits -= attackerLoss;
            playerOneUnits -= defenderLoss;
        }

        // Draws the map and units to the console.
        draw.matrix = draw.initMatrix();
        draw.manipulateMatrix(playerOneFields, 1);
        draw.manipulateMatrix(playerTwoFields, 2);
    }

    private void changeFieldValues (HashMap<String, Integer> attackersFields, HashMap<String, Integer> defendersFields) {
        /*
        Changes the units amount on the specific field and removes it if the total amount is zero.
         */
        attackersFields.put(attackerField, (attackersFields.get(attackerField) - attackerLoss));
        defendersFields.put(defenderField, (defendersFields.get(defenderField) - defenderLoss));
        if (attackersFields.get(attackerField) == 0) {
            attackersFields.remove(attackerField);
        } else if (defendersFields.get(defenderField) == 0) {
            defendersFields.remove(defenderField);
        }
    }
    
    private int getDices(int units, String role) {
        /*
        Returns the dice numbers for the player.
         */
        if (units <= 5) {
            return 1;
        } else if (units <= 10 || role.equals("defender")) {
            return 2;
        } else {
            return 3;
        }
    }

    private void getValidField (HashMap<String, Integer> attacker, HashMap<String, Integer> defender) {
        /*
        Asking the attacker field and the defender field from the attacking player.
         */
        boolean isInMap = false;
        while (!isInMap) {
            attackerField = dice.diceStr("\tAttacker field: ").toUpperCase();
            try {
                attackerUnits = attacker.get(attackerField);
                isInMap = true;
            } catch (NullPointerException e) {
                System.out.println("Not a valid field");
            }
        }
        isInMap = false;
        while (!isInMap) {
            defenderField = dice.diceStr("\tDefender field: ").toUpperCase();
            try {
                defenderUnits = defender.get(defenderField);
                isInMap = true;
            } catch (NullPointerException e) {
                System.out.println("Not a valid field");
            }
        }
    }

    private ArrayList<Integer> diceList (int units) {
        /*
        Returns the list of the dices.
         */
        ArrayList<Integer> diceList = new ArrayList<>();
        for (int i = 0; i < units; i++) {
            int randomNr = random.nextInt(6) + 1;
            diceList.add(randomNr);
        }
        Collections.sort(diceList);
        Collections.reverse(diceList);
        return diceList;
    }

    private void printResult(ArrayList<Integer> diceList) {
        /*
        Prints out the result of the dices.
         */
        for (int i = 0; i < diceList.size(); i++) {
            if (i == diceList.size()-1) {
                System.out.printf("%d\n", diceList.get(i));
            } else {
                System.out.printf("%d-", diceList.get(i));
            }
        }
    }

    private void countLoss () {
        /*
        Counts the amount of the attacker and the defender losses.
         */
        attackerLoss = 0;
        defenderLoss = 0;
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

    private HashMap<String, Integer> initFields (HashMap<String, Integer> playerMap, int fieldNumber, int units) {
        /*
        Fills out the field with units.
         */
        boolean isValid;
        for (int i = 0; i < fieldNumber; i++) {
            if (units == 0) {
                break;
            } else if (i == fieldNumber-1) {
                playerMap.put((worldMap.get(i)), units);
            } else {
                isValid = false;
                while (!isValid) {
                    System.out.printf("\nYou have %d units left...\n", units);
                    int usedUnits = dice.diceInt(String.format("Number of %s units on field \"%s\": ", warriorMap.get(i) ,worldMap.get(i)));
                    if (usedUnits <= units && usedUnits > 0) {
                        playerMap.put((worldMap.get(i)), usedUnits);
                        units -= usedUnits;
                        isValid = true;
                    } else if (usedUnits == 0){
                        isValid = true;
                    } else {
                        System.out.println("\nYou don't have so many units!");
                    }
                }
            }
        }
        return playerMap;
    }
}

