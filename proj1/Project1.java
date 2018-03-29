import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;

/**
 * Created by George Nassour on 3/22/2018.
 * I'm a bad programmer. I want to be a doctor not a software engineer. Please do not grade me down because I am
 * wasteful with my memory :( .
 */

public class Project1 {

    private static Scanner scanner;
    private int menPreferences[][];
    private static int womenPreferences[][];
    private int women[];
    private int tempWomen[];
    private static boolean stableMatching = true;
    private static Point p;


    //Open file using scanner
    private void openFile(){
        try{
            scanner = new Scanner(new File("input1.txt"));
        }catch(FileNotFoundException fnf){
            System.out.println("The file you have specified was not found");
        }
    }

    //Provide the dimensions of the array
    private void setupArray(int dimensions){
        menPreferences = new int[dimensions][dimensions];
        womenPreferences = new int[dimensions][dimensions+1];

    }

    //Use some double for loops to enter the permutations into the array
    private void readResults(int numOfRelationships){
        String permutationString = "";
        for(int i = 0; i<numOfRelationships; i++){
            for(int j = 0; j<numOfRelationships; j++){
                if(scanner.hasNextInt())
                    menPreferences[i][j] = scanner.nextInt();
            }
        }
        for(int i = 0; i<numOfRelationships; i++){
            for(int j = 0; j<numOfRelationships; j++){
                if(scanner.hasNextInt())
                    womenPreferences[i][j] = scanner.nextInt();
            }
        }
        for(int i = 0; i < numOfRelationships; i++){
            womenPreferences[scanner.nextInt()-1][numOfRelationships]= i;
        }
    }

    /**
     * Preprocessing the women array to have men in ascending order of rank
     * End of the women's preference array is their current matchings
     */
    private void womenPreprocessing(int numOfChecks){
        for(int i = 0; i<numOfChecks; i++){
            women = womenPreferences[i];
            tempWomen = new int[women.length];
            for(int j = 0; j<numOfChecks; j++){
                tempWomen[women[j]-1] = j+1;
            }
            tempWomen[numOfChecks] = women[numOfChecks];
            womenPreferences[i] = tempWomen;
        }
    }

    //Close the scanner
    private void closeScanner(){
        scanner.close();
    }

    /**
     *THE MAIN SHINDIG
     * Two for loops
     * The first for loop iterates through the men and gets their preference list
     * The second for loop does a one pass check to see if the woman that the man proposes to prefers him rather than
     * her current choice.
     * If there is an instability, it is saved into a Point object and later printed in main.
     */
    private void checkStability(int numOfChecks){
        for(int i = 0; i<numOfChecks; i++) {
            if (stableMatching) {
                women = menPreferences[i];
                for (int j = 0; j < numOfChecks; j++) {
                    if(womenPreferences[women[j]-1][numOfChecks] == i){
                        j = numOfChecks;
                    }
                    else if (womenPreferences[women[j] - 1][i] < womenPreferences[women[j] - 1][womenPreferences[women[j] - 1][numOfChecks]]) {
                        stableMatching = false;
                        p = new Point(i + 1, women[j]);
                        j = numOfChecks;
                    }
                }
            }
        }
    }

    public static void main(String [] args){
        //The number of men and women
        int n;
        Project1 project = new Project1();
        //Accessing the file
        project.openFile();
        n = scanner.nextInt();
        //Setting up the dimensions of the arrays
        project.setupArray(n);
        //Read results into array
        project.readResults(n);
        //Preprocess the women's array
        project.womenPreprocessing(n);
        //Checking for instabilities
        project.checkStability(n);
        //Printing the status of the stability
        if(!stableMatching){
            System.out.println("No (" + (int)p.getX() +", " + (int)p.getY()+")");
        } else{
          System.out.println("Yes");
        }
        //FINISH
        project.closeScanner();
    }

}

