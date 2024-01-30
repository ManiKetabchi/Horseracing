package horseracing;

import java.util.Scanner;

public class HorseRacing {

     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int currentMenu = 0;    
        HorseRacingHelper.prepareHorseRacingSimulation();
        boolean gameOver = false;
        while(!gameOver){
            HorseRacingHelper.clearConsole();

            int numHorsesInRace = (int) (Math.random() * 3) + 7;

            Race race = HorseRacingHelper.createRace(numHorsesInRace, (int) (Math.random() * 3) + 1, (int) (Math.random() * 3) + 1);
            

            // Main Menu

            while (currentMenu == 0) {
                int mainMenuVar = race.mainMenu(in);
                if (mainMenuVar == 1) {
                    currentMenu = 1;
                } else if (mainMenuVar == 2) {
                    currentMenu = 2;
                } else if (mainMenuVar == 3) {
                    currentMenu = 3;
                } else if (mainMenuVar == 4) {
                    currentMenu = 9999;
                } else {
                    currentMenu = 0;
                }
            }

            // Bank tree

            while (currentMenu == 1) {
                int bankMenuVar = race.bankMenu(in);
                if (bankMenuVar == 1) {
                    currentMenu = 10;
                } else if (bankMenuVar == 2) {
                    currentMenu = 0;
                }
            }

            while (currentMenu == 10) {
                int loanMenuVar = race.loanMenu(in);
                    if (loanMenuVar == 1) {
                        currentMenu = 1;
                    } else if (loanMenuVar == 2) {
                        currentMenu = 0; // MAKE ERROR
                    } else {
                        currentMenu = 0;
                    }
            }

            // Racing Tree

            while (currentMenu == 2) {

                race.displayRaceInfo();
                race.bettingSystem();
                race.startRace();
                race.checkWin();
                currentMenu = 0;
            }


            while (currentMenu == 11) {
                int loanMenuVar = race.loanMenu(in);
                    if (loanMenuVar == 1) {
                        currentMenu = 1;
                    } else if (loanMenuVar == 2) {
                        currentMenu = 0; // MAKE ERROR
                    } else {
                        currentMenu = 0;
                    }
            }

            while (currentMenu == 9999) {
                System.out.println("Thanks for playing");
                System.out.println();
                System.out.println("You won " + race.racesWon + " races!");
                
                try { // wait for 2s
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.exit(0); // exit
            }


        }
    }

    private static boolean playAgain(Scanner in) {
        System.out.print("\u001B[?25l");  // Hide the cursor

        System.out.print("Play Again: (y/n): ");
        String result = in.nextLine();

        if (result.equals("n"))
            return true;

        return false;
    }
}