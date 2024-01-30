package horseracing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses; 
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;

    public static int accountsPayable = 0; // loan amount
    public static int bankBalance = 1000; // initial amount of money (can be changed with loans)
    public static int realBalance = 1000; // initial amount of money (can't be changed with loans)
    public static int racesWon = 0; // races won stat
    public static int betInt; // holder var
    public static int betAmountInt; // holder var
    public static int betPlacingInt; // holder var
    Scanner input = new Scanner(System.in); // scanner

    private List<Horse> results;

    // DESLAURIERS CODE UNTIL LINE 63

    public Race(List<Horse> horses, double raceLength, String raceSurface) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public int numHorses(){
        return horses.size();
    }

    public Horse getCurrentHorse(){
        return horses.get(currentHorse);
    }

    public Horse getNextHorse(){
        if (currentHorse == horses.size())
            currentHorse = 0;
        
        return horses.get(currentHorse++);
    }

    public double getRaceLength() {
        return raceLength;
    }

    public String getRaceSurface() {
        return raceSurface;
    }


    // GROUP CODE STARTS HERE


    public static int mainMenu(Scanner input) { // Main menu for the game
        HorseRacingHelper.clearConsole();
        System.out.println("ICS3U Derby"); // title
        System.out.println();
        System.out.println("1. Bank"); // menu option
        System.out.println("2. Race"); // menu option
        System.out.println("3. Quit"); // menu option
        System.out.print("Enter your choice: ");
    
        int response = 0; // response int 1-3
        int holderInt = 0; // when holderInt is 1 the valid output is returned
    
        while (holderInt == 0) { // loop until holderInt is 1

            if (input.hasNextInt()) { // check if response is an int

                response = input.nextInt();
                if (response >= 1 && response <= 3) { // check if response is 1-3
                    holderInt = 1; // set holderInt to 1 to break the while loop
                } else {
                    System.out.println("Please enter a number between 1 and 3."); // error msg
                }
            } else {
                System.out.println("Please enter a number between 1 and 3."); // error msg
                input.next(); // ignore input
            }
        }

        return response;  // return statement once holderInt is 1 with a valid input
    }

    public static int bankMenu(Scanner input) { // Menu for banking system
        HorseRacingHelper.clearConsole();
        System.out.println("Bank Menu"); // title
        System.out.println();
        System.out.println("Bank Balance: $" + bankBalance); // bank balance
        System.out.println("Accounts Payable: $" + accountsPayable); // amount you owe/loaned for
        System.out.println();
        System.out.println("1. Loan"); // move to loanmenu
        System.out.println("2. Back"); // back to mainmenu

        int response = 0; // response int 1 or 2
        int holderInt = 0; // when holderInt is 1 the valid output is returned
    
        while (holderInt == 0) { 

            if (input.hasNextInt()) { // check if response is an int

                response = input.nextInt();
                if (response >= 1 && response <= 2) { // check if response is 1 or 2
                    holderInt = 1; // set holderInt to 1 to break the while loop
                } else {
                    System.out.println("Please enter a number between 1 and 2."); // error msg
                }
            } else {
                System.out.println("Please enter a number between 1 and 2."); // error msg
                input.next(); // ignore input
            }
        }
        return response;
    }

    public static int loanMenu(Scanner input) { // Menu for loaning system
        HorseRacingHelper.clearConsole();
        System.out.println("Loaning Menu");
        System.out.println();
        System.out.println("Enter the amount you would like to take a loan for (max " + (realBalance*5 - accountsPayable) + ")");
        System.out.println("Enter a negative number to pay back a loan (amount owed: " + accountsPayable + ")");
        System.out.println("Enter 0 to go back");
        
        int response = 0; // response less than 5 times bank balance
        int holderInt = 0; // when holderInt is 1 the valid output is returned
    
        while (holderInt == 0) { 

            if (input.hasNextInt()) { // check if response is an int

                response = input.nextInt();
                if (response >= -1*accountsPayable && response <= realBalance*5 - accountsPayable) { // check if response is to go back or if it goes over the allowed loan amoutn
                    holderInt = 1; // set holderInt to 1 to break the while loop
                } else {
                    System.out.println("Please enter an integer between - $" + accountsPayable + " and $" + ((realBalance * 5)-accountsPayable)); // error msg
                }
            } else {
                System.out.println("Please enter an integer between - $" + accountsPayable + " and $" +  ((realBalance * 5)-accountsPayable)); // error msg
                input.next(); // ignore input
            }
        }
        bankBalance += response;
        accountsPayable += response;
        
        return response;
    }
    
    public void displayHorseTable(){ // Displays the horse table

        System.out.printf("|%-23s|%10s|%11s|%10s|%15s|%10s|%15s|%15s|\n", "Horse", "Dirt Stats", "Grass Stats", "Mud Stats", "Preferred Length", "Win Reward", "Place Reward", "Show Reward");

        for (int i = 0; i < horses.size(); i++) {   // iterates through the horses list
            Horse horse = horses.get(i);
            String s1 = "" + horse.getName();
            String s2 = "" + horse.getDirtRating();
            String s3 = "" + horse.getGrassRating();
            String s4 = "" + horse.getMudRating();
            String s5 = "" + horse.getPreferredLength();
            String iNum = "" + (i+1);
            String s6 = "" + racingProbabilitiesWin(horse);
            String s7 = "" + racingProbabilitiesPlace(horse);
            String s8 = "" + racingProbabilitiesShow(horse);
           // String s7 = "" + racingProbabilitiesPlace(horse);
           // String s8 = "" + racingProbabilitiesShow(horse);
            

            

            System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%2s|%-20s|%10s|%11s|%10s|%15s|%10s|%15s|%15s|\n",iNum, s1, s2, s3, s4, s5, s6, s7, s8);
        }
        System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
    }

    public void bettingSystem() { // Betting system for the game 
        int holderInt1 = 0;
        int holderInt2 = 0;
        int holderInt3 = 0;

        System.out.print("Enter the number of the horse you would like to bet on: ");


        while (holderInt1 == 0) { 

            if (input.hasNextInt()) { // check if response is an int

                betInt = input.nextInt();
                if (betInt >= 1 && betInt <= numHorses()) { // check if response is in range of horses
                    holderInt1 = 1; // set holderInt1 to 1 to break the while loop
                } else {
                    System.out.println("Please enter a number between 1 and " + numHorses()); // error msg
                }
            } else {
                System.out.println("Please enter a number between 1 and " + numHorses()); // error msg
                input.next(); // ignore input
            }
        }

        System.out.println();


        System.out.println("$" + bankBalance + " is your current bank balance"); // prompt user
        System.out.println("You owe $" + accountsPayable + " to the bank");

        System.out.print("Enter the amount you would like to bet for (minumum 50): ");
        
        while (holderInt2 == 0) { 

            if (input.hasNextInt()) { // check if response is an int

                betAmountInt = input.nextInt();
                if (betAmountInt >= 50 && betAmountInt <= bankBalance) { // check if response is in range
                    holderInt2 = 1; // set holderInt2 to 1 to break the while loop
                } else {
                    System.out.println("Please enter a number between 50 and " + bankBalance); // error msg
                }
            } else {
                System.out.println("Please enter a number between 50 and " + bankBalance); // error msg
                input.next(); // ignore input
            }
        }

        System.out.println();

        System.out.println("1. Win"); // option
        System.out.println("2. Place"); // option
        System.out.println("3. Show"); // option
        
        System.out.print("Enter the placing you would like to bet on: "); // ask user for input

        while (holderInt3 == 0) { 

            if (input.hasNextInt()) { // check if response is an int

                betPlacingInt = input.nextInt();
                if (betPlacingInt >= 1 && betPlacingInt <= 3) { // check if response is 1, 2 or 3
                    holderInt3 = 1; // set holderInt3 to 1 to break the while loop
                } else {
                    System.out.println("Please enter a number between 1 and 3"); // error msg
                }
            } else {
                System.out.println("Please enter a number between 1 and 3"); // error msg
                input.next(); // ignore input
            }
        }

        System.out.println();
    }

    public void displayRaceInfo() { // displays race information
        displayHorseTable();

        System.out.println();
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
    }

    public void checkWin() { // check if user won or not and updates bank balance accordingly
        System.out.println((1) + ": " + results.get(0).getName() + "("+results.get(0).getNumber()+")");
        if (betInt == (results.get(0).getNumber()) && betPlacingInt == 1) { // check for win
            bankBalance += Math.round(betAmountInt * winVariable((results.get(0))));
            realBalance += Math.round(betAmountInt * winVariable((results.get(0))));

            System.out.println("YOU WIN!");
            System.out.println("You now have $" + bankBalance);

            racesWon += 1;

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        } else if (betPlacingInt == 1) { // if false
            bankBalance -= betAmountInt;
            realBalance -= betAmountInt;
            System.out.println("YOU LOSE!");
            System.out.println("You now have $" + bankBalance);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        }
        if (betInt == (results.get(0).getNumber()) && betPlacingInt == 2) { // check for place
            bankBalance += Math.round(betAmountInt * placeVariable((results.get(0))));
            realBalance += Math.round(betAmountInt * placeVariable((results.get(0))));

            racesWon += 1;

            System.out.println("YOU WIN");
            System.out.println("You now have $" + bankBalance);
        } else if (betPlacingInt == 2) { // if false
            bankBalance -= betAmountInt;
            realBalance -= betAmountInt;

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        if (betInt == (results.get(0).getNumber()) && betPlacingInt == 3) { // check for show
            bankBalance += Math.round(betAmountInt * showVariable((results.get(0))));
            realBalance += Math.round(betAmountInt * showVariable((results.get(0))));

            racesWon += 1;

            System.out.println("YOU WIN");
            System.out.println("You now have $" + bankBalance);
        } else if (betPlacingInt == 3) { // if false
            bankBalance -= betAmountInt;
            realBalance -= betAmountInt;
            

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public void displayResults(){ // results
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            System.out.println((i+1) + ": " + results.get(i).getName() + "("+results.get(i).getNumber()+")");
        }
    }

    public void startRace(){
        resetHorses();
        int numSpaces = (int)(raceLength*10);
        boolean done = false;
        HorseRacingHelper.pauseForMilliseconds(1000);
        HorseRacingHelper.playBackgroundMusicAndWait("Race.wav");
        HorseRacingHelper.playBackgroundMusic("horse_gallop.wav", true);

        
        while(!done){
            HorseRacingHelper.pauseForMilliseconds(40);
            HorseRacingHelper.clearConsole();
            HorseRacingHelper.updateTrack(numSpaces, horses);
            Horse horse = getNextHorse();
           

            if(!horse.raceFinished() && horse.getCurrentPosition() >= numSpaces){
                results.add(horse);
                horse.setRaceFinished(true);
            } else if(!horse.raceFinished()){
                horse.incrementPosition(getIncrementForHorse(horse));
            }

            displayResults();

            if (results.size() == horses.size())
                done = true;
        }

        HorseRacingHelper.stopMusic();
    }
    // Other me`thods for simulating the race, calculating winners, etc., can be added as needed
    private int getIncrementForHorse(Horse horse) {

        int distanceFactor = (int)(7 - Math.abs(horse.getPreferredLength() - this.raceLength)); // get distance factor by minusing the diffrence of the horses preferred length and the actual racelength from 10

        //if (raceSurface.equals("grass"))
        //    distanceFactor += horse.getGrassRating()

        
        if (raceSurface.equals("Dirt"))
            distanceFactor += horse.getDirtRating();
        if (raceSurface.equals("Grass"))
            distanceFactor += horse.getGrassRating();
        if (raceSurface.equals("Mud"))
            distanceFactor += horse.getMudRating();
        // this.raceLength
        // this.raceSurface
       return (int)(distanceFactor / ((int) (Math.random() * 3) + 1));
    }

    private int getPureIncrement(Horse horse) {

        int distanceFactor = (int)(10 - Math.abs(horse.getPreferredLength() - this.raceLength)); // get distance factor by minusing the diffrence of the horses preferred length and the actual racelength from 10

        //if (raceSurface.equals("grass"))
        //    distanceFactor += horse.getGrassRating()

        
        if (raceSurface.equals("Dirt"))
            distanceFactor += horse.getDirtRating();
        if (raceSurface.equals("Grass"))
            distanceFactor += horse.getGrassRating();
        if (raceSurface.equals("Mud"))
            distanceFactor += horse.getMudRating();
        // this.raceLength
        // this.raceSurface
       return (int)(distanceFactor);
    }


    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }

    // Probabilities for horse racing

    public String racingProbabilitiesWin(Horse horse) { // win probability (VISUAL)
        double denom = 0;
        double num = getPureIncrement(horse);

        for (int i = 0; i < horses.size(); i++) {
            denom += getPureIncrement(horses.get(i));
        }

        double ratio = denom / num; // get initial ratio (pure)
        int roundedRatio = (int) Math.round(ratio); // round ratio
        int roundedDenom = (int) Math.round(denom / roundedRatio); // round denom
        int roundedNum = (int) Math.round(num / roundedRatio); // round num

        while (roundedDenom > 9 && roundedNum > 1) { // reduce the fraction
            roundedDenom /= 2;
            roundedNum /= 2;
        }

        int gcd = gcd(roundedDenom, roundedNum); // variable gcd for easy use

        roundedDenom /= gcd; // reduce
        roundedNum /= gcd; // reduce
        if (roundedDenom <= 4) {
            roundedDenom = 5;
        }
        if (roundedNum < 1) {
            roundedNum = 1;
        }
        return roundedDenom + "-" + roundedNum; // return the odds
    }

    public double winVariable(Horse horse) { // win probability for betting
        double denom = 0;
        double num = getPureIncrement(horse);

        for (int i = 0; i < horses.size(); i++) {
            denom += getPureIncrement(horses.get(i));
        }

        double ratio = denom / num; // get initial ratio (pure)
        int roundedRatio = (int) Math.round(ratio); // round ratio
        int roundedDenom = (int) Math.round(denom / roundedRatio); // round denom
        int roundedNum = (int) Math.round(num / roundedRatio); // round num

        while (roundedDenom > 9 && roundedNum > 1) { // reduce the fraction
            roundedDenom /= 2;
            roundedNum /= 2;
        }

        int gcd = gcd(roundedDenom, roundedNum); // variable gcd for easy use

        roundedDenom /= gcd; // reduce
        roundedNum /= gcd; // reduce
        if (roundedDenom <= 4) {
            roundedDenom = 5;
        }
        if (roundedNum < 1) {
            roundedNum = 1;
        }
        return (roundedDenom / roundedNum); // return the odds
    }

    public String racingProbabilitiesPlace(Horse horse) { // win probability (VISUAL)
        double denom = 0;
        double num = getPureIncrement(horse) ;

        for (int i = 0; i < horses.size(); i++) {
            denom += getPureIncrement(horses.get(i));
        }

        double ratio = denom / num; // get initial ratio (pure)
        int roundedRatio = (int) Math.round(ratio); // round ratio
        int roundedDenom = (int) Math.round(denom / roundedRatio); // round denom
        int roundedNum = (int) Math.round(num / roundedRatio); // round num

        while (roundedDenom > 9 && roundedNum > 1) { // reduce the fraction
            roundedDenom /= 2;
            roundedNum /= 2;
        }

        int gcd = gcd(roundedDenom, roundedNum); // variable gcd for easy use

        roundedDenom /= gcd; // reduce
        roundedNum /= gcd; // reduce
        if (roundedDenom <= 4) {
            roundedDenom = 5;
        }
        if (roundedNum < 1) {
            roundedNum = 1;
        }
        return roundedDenom - 1 + "-" + roundedNum; // return the odds
    }

    public double placeVariable(Horse horse) { // win probability for betting
        double denom = 0;
        double num = getPureIncrement(horse) ;

        for (int i = 0; i < horses.size(); i++) {
            denom += getPureIncrement(horses.get(i));
        }

        double ratio = denom / num; // get initial ratio (pure)
        int roundedRatio = (int) Math.round(ratio); // round ratio
        int roundedDenom = (int) Math.round(denom / roundedRatio); // round denom
        int roundedNum = (int) Math.round(num / roundedRatio); // round num

        while (roundedDenom > 9 && roundedNum > 1) { // reduce the fraction
            roundedDenom /= 2;
            roundedNum /= 2;
        }

        int gcd = gcd(roundedDenom, roundedNum); // variable gcd for easy use

        roundedDenom /= gcd; // reduce
        roundedNum /= gcd; // reduce
        if (roundedDenom <= 4) {
            roundedDenom = 5;
        }
        if (roundedNum < 1) {
            roundedNum = 1;
        }
        return (roundedDenom - 1) / roundedNum; // return the odds
    }

    public String racingProbabilitiesShow(Horse horse) { // win probability (VISUAL)
        double denom = 0;
        double num = getPureIncrement(horse);

        for (int i = 0; i < horses.size(); i++) {
            denom += getPureIncrement(horses.get(i));
        }

        double ratio = denom / num; // get initial ratio (pure)
        int roundedRatio = (int) Math.round(ratio); // round ratio
        int roundedDenom = (int) Math.round(denom / roundedRatio); // round denom
        int roundedNum = (int) Math.round(num / roundedRatio); // round num

        while (roundedDenom > 9 && roundedNum > 1) { // reduce the fraction
            roundedDenom /= 2;
            roundedNum /= 2;
        }

        int gcd = gcd(roundedDenom, roundedNum); // variable gcd for easy use

        roundedDenom /= gcd; // reduce
        roundedNum /= gcd; // reduce
        if (roundedDenom <= 4) {
            roundedDenom = 5;
        }
        if (roundedNum < 1) {
            roundedNum = 1;
        }
        return roundedDenom - 3 + "-" + roundedNum; // return the odds
    }

    public double showVariable(Horse horse) { // win probability for betting
        double denom = 0;
        double num = getPureIncrement(horse);

        for (int i = 0; i < horses.size(); i++) {
            denom += getPureIncrement(horses.get(i));
        }

        double ratio = denom / num; // get initial ratio (pure)
        int roundedRatio = (int) Math.round(ratio); // round ratio
        int roundedDenom = (int) Math.round(denom / roundedRatio); // round denom
        int roundedNum = (int) Math.round(num / roundedRatio); // round num

        while (roundedDenom > 9 && roundedNum > 1) { // reduce the fraction
            roundedDenom /= 2;
            roundedNum /= 2;
        }

        int gcd = gcd(roundedDenom, roundedNum); // variable gcd for easy use

        roundedDenom /= gcd; // reduce
        roundedNum /= gcd; // reduce
        if (roundedDenom <= 4) {
            roundedDenom = 5;
        }
        if (roundedNum < 1) {
            roundedNum = 1;
        }
        return (roundedDenom - 3) / roundedNum; // return the odds
    }


    public int gcd(int a, int b) { // gcd for easy use
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}