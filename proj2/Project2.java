import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Project2{
    //3D array to save the work in our recursive calls
    private static String [][][] three_string_array;
    private static String subsequence_string_1, subsequence_string_2, subsequence_string_3;
    private static Scanner scanner;
    private static int i,j,k;


    //Main
    public static void main(String [] args){
        //Define scanner object and load in file
        try{
            scanner = new Scanner(new File("./input2.txt"));
        }catch(FileNotFoundException fnf){
            System.out.println("Something went wrong with your file \n" + fnf.toString());
        }
        //Load strings up from file
        subsequence_string_1 = scanner.next();
        subsequence_string_2 = scanner.next();
        subsequence_string_3 = scanner.next();
        //Obtain the lengths of the strings
        i = subsequence_string_1.length();
        j = subsequence_string_2.length();
        k = subsequence_string_3.length();
        //Declare 3D array size
        three_string_array = new String [i][j][k];

    }
    
    //
    public static void least_Common_Substring(String three_string_array[][][]){

    }
}