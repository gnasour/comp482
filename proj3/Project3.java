/**
 * Created by George Nassour on 5/7/2018.
 * Project 3 - MAX SAT
 * Find a truth assignment that satisfies as many disjunctions as possible
 * When running, press 1 for brute force optimal method or 2 for polynomial time non-optimal method
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Project3{

    private static Scanner scanner;
    //These two arraylists will act as a column on the input
    //One and Two corrsepond to the relative columns
    private static ArrayList<Integer> satArrayListOne = new ArrayList<Integer>();
    private static ArrayList<Integer> satArrayListTwo = new ArrayList<Integer>();
    //This is the largest variable in the file after applying Math.abs to it
    private static int largestVariable = 0;
    //After reaching the most significant bit, reduce this variable by one to not cause overflow
    private static int maxVariation = 0;
    //Do all variables false first before changing the boolean array
    private static boolean triggerAllFalseResult = false;
    //Boolean array that holds the variations for the truth values of the variables
    private static boolean booleanArray[];
    //The boolean array that had the most satisified truth assignments
    private static boolean bestBooleanArray[];
    //Used to calculate the number of times a variable appears in the text file
    private static int probabilityArray[][];
    //The most disjunctions we have satisfied
    private static int mostVariables = 0;
    //How many subsets we have to do
    //Brute forced sets
    private static long calculateSubsets = 0;
    //Binary addition on boolean array
    //If we changed a position to true(1) then do not change anymore booleans(bits)
    private static boolean changedToTrue = false;
    //The truth assignments of the boolean array in string format
    private static String truthValueString = "";

    /**
     Main method to start off our application.
     Tasks:
     1.) Scans input 3 text for the inputs
     2.) Ask user which algorithm to use
     3.) Use the algorithm chosen to compute the result
     */
    public static void main(String [] args){
        //Used to store what the user's choice in method to compute
        int choice;

        //Read the file into scanner
        try{
            scanner = new Scanner(new File("input3.txt"));
        }catch(FileNotFoundException fnf){
            System.out.println("The file you have specified was not found: \n" + fnf.toString());
        }

        //Read the numbers into two arraylists
        //The ArrayLists one and two correspond to the columns of the variables in the text file
        while(scanner.hasNextInt()){
            satArrayListOne.add(scanner.nextInt());
            satArrayListTwo.add(scanner.nextInt());
        }

        //Find the largest variable
        for(int i = 0; i < satArrayListTwo.size(); i++){
            if(Math.abs((int)satArrayListOne.get(i)) > largestVariable){
                largestVariable = Math.abs((int)satArrayListOne.get(i));
            }
            if(Math.abs((int)satArrayListTwo.get(i)) > largestVariable){
                largestVariable = Math.abs((int)satArrayListTwo.get(i));
            }
        }

        //boolean array for brute forcing
        booleanArray = new boolean[largestVariable];
        //Used to calculate the recurrence of certain variables
        probabilityArray = new int[largestVariable][2];
        //Calculate the max times around the for loop for brute force
        calculateSubsets = (long)Math.pow(2, largestVariable);
        //Use this variable to keep track of most significant bit
        maxVariation = largestVariable;


        //User input for algorithm
        System.out.print("Enter 1 for Brute Force or 2 for Best Polynomial Algorithm: ");
        scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        if(choice == 1){
            use_brute_force();
        }else if(choice == 2){
            use_best_poly_algo();
        }else
            System.out.print("Please enter only 1 or 2 when you run the program next time");

    }

    /**
     * Uses brute force to find the solution of the SAT problem
     * This algorithm computes all subsets of the problem to find the optimal solution
     * 2^n subsets + n comparisons
     */
    public static void use_brute_force() {

        for (long i = 0; i < calculateSubsets; i++) {
            //Use the initial all-false array
            //Then start doing the bit addition to change the boolean values
            if(triggerAllFalseResult)
                changeArray();
            else
                triggerAllFalseResult = true;
            //This variable counts the most satisfied in one instance of the brute force
            int localMostVariable = 0;
            for (int j = 0; j < satArrayListOne.size(); j++) {
                int truthValueOne = (int) satArrayListOne.get(j);
                int truthValueTwo = (int) satArrayListTwo.get(j);
                boolean truthAssignmentOne = booleanArray[Math.abs(truthValueOne)-1];
                boolean truthAssignmentTwo = booleanArray[Math.abs(truthValueTwo)-1];
                if((truthAssignmentOne == false && truthValueOne < 0) || (truthAssignmentOne == true && truthValueOne > 0)){
                    localMostVariable += 1;
                }else if((truthAssignmentTwo == false && truthValueTwo < 0) || (truthAssignmentTwo == true && truthValueTwo > 0)){
                    localMostVariable += 1;
                }
            }
            if(localMostVariable > mostVariables){
                mostVariables = localMostVariable;
                bestBooleanArray = booleanArray.clone();
            }

        }
        System.out.print(mostVariables + " ");
        for(int i = 0; i < bestBooleanArray.length; i++)
            if(bestBooleanArray[i]){
                truthValueString += "T";
            }else{
                truthValueString += "F";
            }
        System.out.print(truthValueString);

    }

    /**
     * Using a statistical method to find the solution of the SAT problem
     * 2n to compute the ratio of variables to satisfy
     * n to compute the boolean array
     * n to compute the correct amount of disjunctions
     * n to write result to string
     * O(n) time complexity
     */
    public static void use_best_poly_algo(){
        calculateProbability();
        for(int i = 0; i < booleanArray.length; i++){
            if(probabilityArray[i][0] > probabilityArray[i][1]){
                booleanArray[i] = true;
            }else if(probabilityArray[i][0] == probabilityArray[i][1]){
                Random random = new Random();
                int coinFlip = random.nextInt(2);
                if(coinFlip == 0){
                    booleanArray[i] = true;
                }else{
                    booleanArray[i] = false;
                }

            }else{
                booleanArray[i] = false;
            }
        }
        for (int j = 0; j < satArrayListOne.size(); j++) {
            int truthValueOne = (int) satArrayListOne.get(j);
            int truthValueTwo = (int) satArrayListTwo.get(j);
            boolean truthAssignmentOne = booleanArray[Math.abs(truthValueOne)-1];
            boolean truthAssignmentTwo = booleanArray[Math.abs(truthValueTwo)-1];
            if((truthAssignmentOne == false && truthValueOne < 0) || (truthAssignmentOne == true && truthValueOne > 0)){
                mostVariables += 1;
            }else if((truthAssignmentTwo == false && truthValueTwo < 0) || (truthAssignmentTwo == true && truthValueTwo > 0)){
                mostVariables += 1;
            }
        }
        System.out.print(mostVariables + " ");
        for(int i = 0; i < booleanArray.length; i++)
            if(booleanArray[i]){
                truthValueString += "T";
            }else{
                truthValueString += "F";
            }
        System.out.print(truthValueString);
    }
    /**
     * Calculates the amount of variables and checks how many are inverted or not
     * One for loop for each arraylist
     */
    public static void calculateProbability(){
        for(int variableNumber:satArrayListOne){
            if(variableNumber > 0)
                probabilityArray[Math.abs(variableNumber)-1][0] += 1;
            else
                probabilityArray[Math.abs(variableNumber)-1][1] += 1;
        }
        for(int variableNumber:satArrayListTwo){
            if(variableNumber > 0)
                probabilityArray[Math.abs(variableNumber)-1][0] += 1;
            else
                probabilityArray[Math.abs(variableNumber)-1][1] += 1;
        }
    }

    /**
     * Use a boolean array and compute binary addition on it until you have exhausted all truth assignment possibilities
     */
    public static void changeArray(){
        for (int i = 0; i <= maxVariation; i++){
            if ( i != maxVariation) {
                if (!changedToTrue) {
                    if (!booleanArray[i]) {
                        booleanArray[i] = true;
                        changedToTrue = true;
                    } else if (booleanArray[i]) {
                        booleanArray[i] = false;
                    }
                }else
                    break;
            }else{
                if (!changedToTrue) {
                    if (!booleanArray[i]) {
                        booleanArray[i] = true;
                        changedToTrue = true;
                    } else if (booleanArray[i]) {
                        booleanArray[i] = false;
                    }
                }
                maxVariation -= 1;
                break;
            }
        }
        //Reset the boolean to false to indicate the the array can be changed again
        changedToTrue = false;

    }
}
