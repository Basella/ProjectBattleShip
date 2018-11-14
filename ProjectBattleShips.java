import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
//
public class ProjectBattleShip {
    public static Scanner input = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        String[][] sea = new String[10][10];
        System.out.println("****Welcome to Battle Ships Game****");
        System.out.println("Right now, the sea is empty");
        System.out.println();
        String[][] ocean = oceanMap(sea);
        String[][] userComputerOcean = userComputerChoice(ocean);
        String attack = attackTime(userComputerOcean);
        System.out.println(attack);
    }

    public static String[][] oceanMap(String[][] sea) {
        System.out.println("   0123456789");
        for (int i = 0; i < sea.length; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < sea[i].length; j++) {
                if (sea[i][j]=="1") {
                    sea[i][j] = "@";
                } else {
                    sea[i][j] = " ";
                }
                System.out.print(sea[i][j]);
            }
            System.out.println("| " + i);
        }
        System.out.println("   0123456789");
        return sea;
    }

    public static String[][] userComputerChoice(String[][] userChoiceSea) {
        System.out.println("Please deploy 5 ships");
        int count = 1;
        while (count < 6) {
            System.out.println("Enter X coordinate for your ship: ");
            int userX = input.nextInt();
            System.out.println("Enter Y coordinate for your ship: ");
            int userY = input.nextInt();

            if ((userChoiceSea[userX][userY].equals("@")) || (userChoiceSea[userX][userY].equals("*"))) {
                System.out.println("Sorry, a ship already exists in that location");
                System.out.println("Enter X coordinate for your ship: ");
                userX = input.nextInt();
                System.out.println("Enter Y coordinate for your ship: ");
                userY = input.nextInt();
            } else {
                userChoiceSea[userX][userY] = "1";
                count++;
            }
        }
        System.out.println("5 Ships successfully deployed");
        System.out.println("Computer is deploying ships");

        int counting = 1;
        while (counting < 6) {
            System.out.println("Enter X coordinate for your ship: ");
            int computerChoiceX = rand.nextInt(9);
            System.out.println("Enter Y coordinate for your ship: ");
            int computerChoiceY = rand.nextInt(9);

            if ((userChoiceSea[computerChoiceX][computerChoiceY]=="1") || (userChoiceSea[computerChoiceX][computerChoiceY]=="2")) {
                System.out.println("Sorry, a ship already exists in that location");
                System.out.println("Enter X coordinate for your ship: ");
                computerChoiceX = rand.nextInt(9);
                System.out.println("Enter Y coordinate for your ship: ");
                computerChoiceY = rand.nextInt(9);
            } else {
                userChoiceSea[computerChoiceX][computerChoiceY] = "2";
                System.out.println(counting + " Ship Deployed");
                counting++;
            }
        }
        System.out.println("---------------------------------");
        String[][] callOcean = oceanMap(userChoiceSea);
        return callOcean;
    }

    public static String attackTime(String[][] oceanCheck) {
        int userShips = 5;
        int computerShips = 5;

        while ((userShips > 1) && (computerShips > 1)) {
            System.out.println("YOUR TURN");
            System.out.println("Enter X coordinate: ");
            int userGuessX = input.nextInt();
            System.out.println("Enter Y coordinate: ");
            int userGuessY = input.nextInt();

            if (oceanCheck[userGuessX][userGuessY]=="*") {
                System.out.println("Boom! You sunk the ship");
                oceanCheck[userGuessX][userGuessY] = "!";
                computerShips--;
            } else if (oceanCheck[userGuessX][userGuessY]=="@") {
                System.out.println("Oh no! You sunk your own ship :(");
                oceanCheck[userGuessX][userGuessY] = "x";
                userShips--;
            } else {
                System.out.println("Sorry you missed");
                oceanCheck[userGuessX][userGuessY] = "-";
            }

            int computerGuessX;
            int computerGuessY;
            do {
                System.out.println("COMPUTER'S TURN");
                System.out.println("Enter X coordinate: ");
                computerGuessX = rand.nextInt(9);
                System.out.println("Enter Y coordinate: ");
                computerGuessY = rand.nextInt(9);

                if (oceanCheck[computerGuessX][computerGuessY]=="*") {
                    System.out.println("The Computer sunk one of its own ships");
                    oceanCheck[computerGuessX][computerGuessY] = "!";
                    computerShips--;
                } else if (oceanCheck[computerGuessX][computerGuessY]=="@") {
                    System.out.println("The Computer sunk one of your ships");
                    oceanCheck[computerGuessX][computerGuessY] = "x";
                    userShips--;
                } else {
                    System.out.println("Computer missed");
                    oceanCheck[computerGuessX][computerGuessY] = "0";
                }
            } while (oceanCheck[computerGuessX][computerGuessY] != "0");
        }
        if (userShips > 1) {
            String playerWins = "Hooray! You won the battle :)";
            System.out.println(playerWins);
        } else {
            String computerWins = "Sorry you lose :(";
            System.out.println(computerWins);
        }

//        for (String[] i : oceanCheck){
//            System.out.println(Arrays.toString(i));
//        }
        return Arrays.toString(oceanCheck);
    }
}