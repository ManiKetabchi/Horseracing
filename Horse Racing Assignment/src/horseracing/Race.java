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
    public static int creditScore = 400;
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
        String result = input.nextLine();
        int response = Integer.parseInt(result);
        if (response == 1) {
            return 1;
        } else if (response == 2) {
            return 2;
        } else if (response == 3) {
            return 3;
        } else if (response == 4) {
            return 4;
        } else {
            return 0;
        }
    }

    public static int bankMenu(Scanner input) {
        HorseRacingHelper.clearConsole();
        System.out.println("Bank Menu");
        System.out.println();
        System.out.println("Bank Balance: " + bankBalance);
        System.out.println("Accounts Payable: " + accountsPayable);
        System.out.println();
        System.out.println("1. Loan");
        System.out.println("2. Back");
        String result = input.nextLine();
        int response = Integer.parseInt(result);
        return response;
    }

    public static int loanMenu(Scanner input) {
        HorseRacingHelper.clearConsole();
        System.out.println("Loaning Menu");
        System.out.println();
        System.out.println("Enter the amount you would like to take a loan for (max " + (realBalance*5 - accountsPayable) + ")");
        System.out.println("Enter 0 to go back");

        String result = input.nextLine();
        int response = Integer.parseInt(result);
        if (response >= 0 && response <= realBalance*5 - accountsPayable) {
            accountsPayable += response;
            bankBalance += response;
            return 1;
        } else if (response == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    
    
    public void displayHorseTable(){
        for (int i = 0; i < horses.size(); i++) {   // iterates through the horses list
            Horse horse = horses.get(i);
            String s1 = "" + horse.getName();
            String s2 = "" + horse.getDirtRating();
            String s3 = "" + horse.getGrassRating();
            String s4 = "" + horse.getMudRating();

            System.out.println("+--------------------+-----+-----+-----+");
            System.out.printf("|%-20s|%5s|%5s|%5s|\n", s1, s2, s3, s4);
        }
        System.out.println("+--------------------+-----+-----+-----+");
    }

    public void displayRaceInfo() {
        System.out.println("Race Information:");
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
        System.out.println("List of Horses:");
        // for (Horse horse : horses) {
        //     System.out.println("- " + horse.getName());
        // }
        displayHorseTable();
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

        // horse.getDirtRating()
        // horse.getMudRating()
        // horse.getPreferredLength()
        // horse.getGrassRating()

        int d = (int)(7 - Math.abs(horse.getPreferredLength() - this.raceLength));

        if (raceSurface.equals("grass"))
            d += horse.getGrassRating() / 2;
        // this.raceLength
        // this.raceSurface
       return (int)(Math.random() * 9);
    }


    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }
}