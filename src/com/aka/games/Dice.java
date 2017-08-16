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
        DiceMap draw;

    public Dice() {
    }

    public void game() {
        this.draw = new DiceMap();
        int fieldNumber;
        int totalUnits;
        InputHandler dice = new InputHandler();

        /*
        Asking the field and the unit numbers from the users.
         */
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

        /*
        Placing the units to the fields.
         */
        System.out.print("\nPlayer 1 distribute units for the fields\n");
        playerOneFields = initFields(playerOneFields, fieldNumber, playerOneUnits);
        draw.manipulateMatrix(playerOneFields, 1);

        System.out.println(playerOneFields);

        System.out.print("\nPlayer 2 distribute units for the fields\n");
        playerTwoFields = initFields(playerTwoFields, fieldNumber, playerTwoUnits);
        draw.manipulateMatrix(playerTwoFields, 2);
        System.out.println(playerTwoFields);

        /*
        Running the game.
         */
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
        System.out.println("GameOver");
    }

    public void playerTurn(int player) {
        int attackerDices;
        int defenderDices;


        System.out.printf("\n\nPlayer %d turn!\n", player);

        /*
        Asking the attacker field and the defender field from the attacking player.
         */
        if (player == 1) {
            getValidField(playerOneFields, playerTwoFields);
        } else {
            getValidField(playerTwoFields, playerOneFields);
        }

        attackerDices = getDices(attackerUnits, "attacker");
        defenderDices = getDices(defenderUnits, "defender");
        attackerDiceList = diceList(attackerDices);
        defenderDiceList = diceList(defenderDices);
        System.out.println("\nDice:");
        System.out.print("\tAttacker: ");
        printResult(attackerDiceList);
        System.out.print("\tDefender: ");
        printResult(defenderDiceList);
        System.out.println("\nOutcome: ");
        countLoss();
        System.out.printf("\tAttacker: Lost %d unit\n", attackerLoss);
        System.out.printf("\tDefender: Lost %d unit\n", defenderLoss);
        if (player == 1) {
            playerOneFields.put(attackerField, (playerOneFields.get(attackerField) - attackerLoss));
            playerOneUnits -= attackerLoss;
            playerTwoFields.put(defenderField, (playerTwoFields.get(defenderField) - defenderLoss));
            playerTwoUnits -= defenderLoss;
            if (playerOneFields.get(attackerField) == 0) {
                playerOneFields.remove(attackerField);
            }
            if (playerTwoFields.get(defenderField) == 0) {
                playerTwoFields.remove(defenderField);
            }
        } else {
            playerTwoFields.put(attackerField, (playerTwoFields.get(attackerField) - attackerLoss));
            playerTwoUnits -= attackerLoss;
            playerOneFields.put(defenderField, (playerOneFields.get(defenderField) - defenderLoss));
            playerOneUnits -= defenderLoss;
            if (playerTwoFields.get(attackerField) == 0) {
                playerTwoFields.remove(attackerField);
            }
            if (playerOneFields.get(defenderField) == 0) {
                playerOneFields.remove(defenderField);
            }
        }
        draw.matrix = draw.initMatrix();
        draw.manipulateMatrix(playerOneFields, 1);
        draw.manipulateMatrix(playerTwoFields, 2);

        System.out.printf("\nPlayer 1 fields: %s\nPlayer 2 fields %s\n", playerOneFields, playerTwoFields);
    }

    public int getDices(int units, String role) {
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

    public void getValidField (HashMap<String, Integer> attacker, HashMap<String, Integer> defender) {
        /*
        Asking the attacker field and the defender field from the attacking player.
         */
        InputHandler dice = new InputHandler();
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

    public ArrayList diceList (int units) {
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

    public void printResult(ArrayList<Integer> diceList) {
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

    public void countLoss () {
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

    public HashMap<String, Integer> initFields (HashMap<String, Integer> playerMap, int fieldNumber, int units) {
        /*
        This looks awful, but I'm working on it.
         */
        InputHandler map = new InputHandler();
        boolean isValid;
        for (int i = 0; i < fieldNumber; i++) {
            if (units == 0) {
                break;
            }
            if (i == fieldNumber-1) {
                playerMap.put((worldMap.get(i)), units);
            } else {
                isValid = false;
                while (!isValid) {
                    System.out.printf("\nYou have %d units left...\n", units);
                    int usedUnits = map.diceInt(String.format("Number of %s units on field \"%s\": ", warriorMap.get(i) ,worldMap.get(i)));
                    if (usedUnits <= units) {
                        if (usedUnits > 0) {
                            playerMap.put((worldMap.get(i)), usedUnits);
                            units -= usedUnits;
                            isValid = true;
                        } else {
                            isValid = true;
                        }
                    } else {
                        System.out.println("\nYou don't have so many units!");
                    }
                }
            }
        }
        return playerMap;
    }
}

