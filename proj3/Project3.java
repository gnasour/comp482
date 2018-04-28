import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Project3{

    private static Scanner scanner;
    /**
        Main method to start off our application.
        Tasks: 
        1.) Scans input 3 text for the inputs
        2.) 
    */
    public static void main(String [] args){
        int choice, answer;
        try{
        scanner = new Scanner(new File("input3.txt"));
        }catch(FileNotFoundException fnf){
            System.out.println("The file you have specified was not found: \n" + fnf.toString());
        }
        scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        answer = (choice == 1) ? use_brute_force() : use_best_poly_algo();

    }

    public static int use_brute_force(){
        //Change the return statement
        return 0;
    }

    public static int use_best_poly_algo(){
        //Change the return statement
        return 0;
    }
}