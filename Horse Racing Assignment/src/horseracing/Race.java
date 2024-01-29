package horseracing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    

    public static int accountsPayable = 0;
    public static int bankBalance = 1000;
    public static int realBalance = 1000;

    public static int betInt;
    public static int betAmountInt;
    public static int betPlacingInt;
    Scanner input = new Scanner(System.in);

    private List<Horse> results;


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

    public static int mainMenu(Scanner input) { 
        HorseRacingHelper.clearConsole();
        System.out.println("Kentucky Derby");
        System.out.println();
        System.out.println("1. Bank");
        System.out.println("2. Race");
        System.out.println("3. Stats");
        System.out.println("4. Settings");
    
        int response = 0; // response int 1-4
        int holderInt = 0; // when holderInt is 1 the valid output is returned
    
        while (holderInt == 0) { 

            if (input.hasNextInt()) { // check if response is an int

                response = input.nextInt();
                if (response >= 1 && response <= 4) { // check if response is 1-4
                    holderInt = 1; // set holderInt to 1 to break the while loop
                } else {
                    System.out.println("Please enter a number between 1 and 4."); // error msg
                }
            } else {
                System.out.println("Please enter a number between 1 and 4."); // error msg
                input.next(); // ignore input
            }
        }

        return response; 
    }

    public static int bankMenu(Scanner input) { // Menu for banking system
        HorseRacingHelper.clearConsole();
        System.out.println("Bank Menu");
        System.out.println();
        System.out.println("Bank Balance: $" + bankBalance);
        System.out.println("Accounts Payable: $" + accountsPayable);
        System.out.println();
        System.out.println("1. Loan");
        System.out.println("2. Back");

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

    
    
    public void displayHorseTable(){

        System.out.printf("|%-23s|%10s|%10s|%10s|%15s|%10s|%10s|%10s|\n", "Horse", "Dirt Stats", "Grass Stats", "Mud Stats", "Preferred Len", "Win Prob", "Place Prob", "Show Prob");

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
            

            

            System.out.println("+-------------------------------------------------------------------------+");
            System.out.printf("|%2s|%-20s|%10s|%11s|%10s|%15s|%10s|%10s|%10s|\n",iNum, s1, s2, s3, s4, s5, s6, s7, s8);
        }
        System.out.println("+-------------------------------------------------------------------------+");
    }

    public void bettingSystem() { // Betting system for the game
        System.out.print("Enter the number of the horse you would like to bet on: ");
        String horseNum = input.nextLine();
        System.out.println();


        System.out.println("$" + bankBalance + " is your current bank balance");
        System.out.println("You owe $" + accountsPayable + " to the bank");

        System.out.print("Enter the amount you would like to bet for: ");
        String betAmount = input.nextLine();
        
        System.out.println();

        System.out.println("1. Win");
        System.out.println("2. Place");
        System.out.println("3. Show");
        
        System.out.print("Enter the placing you would like to bet on: ");
        String betPlacing = input.nextLine();

        System.out.println();

        betInt = Integer.parseInt(horseNum);
        betAmountInt = Integer.parseInt(betAmount);
        betPlacingInt = Integer.parseInt(betPlacing);
    }

    public void displayRaceInfo() {
        displayHorseTable();

        System.out.println();
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
    }

    public void checkWin() {
        System.out.println((1) + ": " + results.get(0).getName() + "("+results.get(0).getNumber()+")");
        if (betInt == (results.get(0).getNumber()) && betPlacingInt == 1) {
            bankBalance += Math.round(betAmountInt*winVariable(results.get(0)));

            System.out.println("YOU WIN");
            System.out.println("You now have $" + bankBalance);
        }
    }
    
    public void displayResults(){
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
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    private int getIncrementForHorse(Horse horse) {

        int distanceFactor = (int)(10 - Math.abs(horse.getPreferredLength() - this.raceLength)); // get distance factor by minusing the diffrence of the horses preferred length and the actual racelength from 10

        System.out.print("ijnefrjnsjndf");

        //if (raceSurface.equals("grass"))
        //    distanceFactor += horse.getGrassRating()

        if (raceSurface.equals("Mud"))
            distanceFactor += horse.getMudRating();
        if (raceSurface.equals("Dirt"))
            distanceFactor += horse.getDirtRating();
        if (raceSurface.equals("Grass"))
            distanceFactor += horse.getGrassRating();
        // this.raceLength
        // this.raceSurface
       return (int)(distanceFactor/ ((int) (Math.random() * 3) + 1));
    }


    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }

    // Probabilities for horse racing

    public String racingProbabilitiesWin(Horse horse) { // win probability (VISUAL)
        double prob1 = 0;
        double prob2 = 0;
        int denom = 1;
        
        prob1 = horse.getPreferredLength() + horse.getGrassRating();

        for (int i = 0; i < horses.size(); i++) { 
            prob2 += horses.get(i).getPreferredLength() + horses.get(i).getGrassRating();
        }

        double probable = prob2/prob1;
        
        double prob = probable%1;

        if (prob > 0.4 && prob < 0.6) {
            denom = 2;
            probable = probable * 2;
        }  

        
        int probable2 = (int) Math.round(probable);


        return probable2 + " - " + denom;
    
    }

    public double winVariable(Horse horse) { // win probability (VISUAL)
        double prob1 = 0;
        double prob2 = 0;
        double denom = 1;
        
        prob1 = horse.getPreferredLength() + horse.getGrassRating();

        for (int i = 0; i < horses.size(); i++) { 
            prob2 += horses.get(i).getPreferredLength() + horses.get(i).getGrassRating();
        }

        double probable = prob2/prob1;
        
        double prob = probable%1;

        if (prob > 0.4 && prob < 0.6) {
            denom = 2;
            probable = probable * 2;
        }  

        
        double probable2 = (int) Math.round(probable);


        return (probable2 / denom);
    
    }

    



    public String racingProbabilitiesPlace(Horse horse) { // top 2 probability (VISUAL)
        double prob1 = 0;
        double prob2 = 0;
        int denom = 1;
        
        prob1 = (horse.getPreferredLength() + horse.getGrassRating()) ;

        for (int i = 0; i < horses.size(); i++) { 
            prob2 += (horses.get(i).getPreferredLength() + horses.get(i).getGrassRating());
        }

        double probable = prob2/prob1;
        
        double prob = probable%1;

        if (prob > 0.4 && prob < 0.6) {
            denom = 2;
            probable = probable * 2;
        }

        int probable2 = (int) Math.round(probable * 0.8);

        if (probable2%denom == 0) {
            probable2 -= 1;
        }

        if (probable2 <= denom) {
            denom += 1;
            probable = probable + 3;
        }

        return (int) probable2 + " - " + denom;
    }

    public String racingProbabilitiesShow(Horse horse) { // top 3 probability (VISUAL)
        double prob1 = 0;
        double prob2 = 0;
        int denom = 1;
        
        prob1 = (horse.getPreferredLength() + horse.getGrassRating()) ;

        for (int i = 0; i < horses.size(); i++) { 
            prob2 += (horses.get(i).getPreferredLength() + horses.get(i).getGrassRating());
        }

        double probable = prob2/prob1;
        
        double prob = probable%1;

        if (prob > 0.4 && prob < 0.6) {
            denom = 2;
            probable = probable * 2;
        }

        int probable2 = (int) Math.round(probable * .6);

        if (probable2%denom == 0) {
            probable2 -= 1;
        }

        if (probable2 == denom) {
            denom += 2;
            probable = probable + 3;
        }

        return (int) probable2 + " - " + denom;
    }
}
