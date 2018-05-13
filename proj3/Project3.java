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

class Project3{

    private static Scanner scanner;
    private static ArrayList satArrayListOne = new ArrayList();
    private static ArrayList satArrayListTwo = new ArrayList();
    private static int largestVariable = 0;
    private static int maxVariation = 0;
    private static boolean triggerAllFalseResult = false;
    private static boolean booleanArray[];
    private static boolean bestBooleanArray[];
    //The most lines we have satisfied
    private static int mostVariables = 0;
    private static int calculateSubsets = 0;
    private static boolean changedToTrue = false;
    /**
     Main method to start off our application.
     Tasks:
     1.) Scans input 3 text for the inputs
     2.) Ask user which algorithm to use
     3.) Use the algorithm chosen
     */
    public static void main(String [] args){
        int choice, answer;
        //Read the file into scanner
        try{
            scanner = new Scanner(new File("src/input3.txt"));
        }catch(FileNotFoundException fnf){
            System.out.println("The file you have specified was not found: \n" + fnf.toString());
        }
        //Read the numbers into two arraylists
        //The ArrayLists one and two correspond to the columns of the variables in the text file
        while(scanner.hasNextLine()){
            satArrayListOne.add(scanner.nextInt());
            satArrayListTwo.add(scanner.nextInt());
        }
        //Find the largest variable
        for(int i = 0; i < satArrayListTwo.size(); i++){
            if(Math.abs((int)satArrayListTwo.get(i)) > largestVariable){
                largestVariable = Math.abs((int)satArrayListTwo.get(i));
            }
        }
        //boolean array for brute forcing
        booleanArray = new boolean[largestVariable];
        //Calculate the max times around the for loop for brute force
        calculateSubsets = (int)Math.pow(2, largestVariable);
        //Use this variable to keep track of most significant bit
        maxVariation = largestVariable;
        use_brute_force();
        for(int i = 0; i < booleanArray.length; i++)
            System.out.print(bestBooleanArray[i]+" ");
        System.out.println(mostVariables);


        /*System.out.println("Enter 1 for Brute Force or 2 for Best Polynomial Algorithm");
        scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        answer = (choice == 1) ? use_brute_force() : use_best_poly_algo();*/

    }

    /**
     * Uses brute force to find the solution of the SAT problem
     * @return
     */
    public static void use_brute_force() {

        for (int i = 0; i < calculateSubsets; i++) {
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
                mostVariables =localMostVariable;
                bestBooleanArray = booleanArray.clone();
            }

        }

    }
    /**
     * Using a statistical method to find the solution of the SAT problem
     * @return
     */
    public static int use_best_poly_algo(){
        //Change the return statement
        return 1;
    }

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
