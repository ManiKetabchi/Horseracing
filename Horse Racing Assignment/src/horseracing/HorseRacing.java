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

            int numHorsesInRace = (int)(Math.random()*7)+5;

            Race race = HorseRacingHelper.createRace(numHorsesInRace, HorseRacingHelper.SHORT, HorseRacingHelper.DIRT);
            race.displayRaceInfo();
            
            while (currentMenu == 0) {
                int mainMenuVar = race.mainMenu(in);
                if (mainMenuVar == 1) {
                    currentMenu = 1;
                } else if (mainMenuVar == 2) {
                    currentMenu = 2;
                } else {
                    currentMenu = 0;
                }
            }

            while (currentMenu == 1) {
                int bankMenuVar = race.bankMenu(in);
                if (bankMenuVar == 1) {
                    currentMenu = 11;
                } else if (bankMenuVar == 2) {
                    currentMenu = 0;
                } else {
                    currentMenu = 1;
                }
            }

            while (currentMenu == 2) {
                
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


            //race.displayRaceInfo();
            //race.startRace();
            

            //System.out.println("Race is Over");
            //gameOver = playAgain(in);


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