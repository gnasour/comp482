/**
 * Created by George Nassour on 5/7/2018.
 * Project 3 - MAX SAT
 * Find a truth assignment that satisfies as many 
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
    private static boolean booleanArray[];
    private static String booleanMatching;
    private static String bestBooleanMatching;
    private static int mostVariables;
    private static int calculateSubsets;
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




        /*System.out.println("Enter 1 for Brute Force or 2 for Best Polynomial Algorithm");
        scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        answer = (choice == 1) ? use_brute_force() : use_best_poly_algo();*/

    }

    /**
     * Uses brute force to find the solution of the SAT problem
     * @return
     */
    public static int use_brute_force() {
        while (calculateSubsets != 0) {
            for (int i = 0; i < calculateSubsets; i++) {

                //This variable counts the most satisfied in one instance of the brute force
                int localMostVariable = 0;
                for (int j = 0; j < satArrayListOne.size(); j++) {
                    int truthValueOne = (int) satArrayListOne.get(j);
                    int truthValueTwo = (int) satArrayListTwo.get(j);

                }

                for (boolean j : booleanArray) {
                    if (j) {
                        mostVariables += 1;
                    }

                }

            }

        }
        return 0;
    }
    /**
     * Using a statistical method to find the solution of the SAT problem
     * @return
     */
    public static int use_best_poly_algo(){
        //Change the return statement
        return 1;
    }
}
