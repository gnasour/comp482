/**
George Nassour
Comp 482 Mon-Wed 11:00-12:15
Project 2- Longest Common Subsequence using Dynamic Programming
This application uses concepts found in dyanmic programming to solve the longest common subsequence problem
Three for loops are used to traverse a 3D array and update it in accordance with rules specified in class 
1.) If a character is matched in all three positions of a string then we increment the position of the array by 1 and move on to the next column and/or row(as well as another character on a string)
2.) If the characters do not all match at some position on all three strings then we just move to another position on the string and array
*/

//Some things we need to import for files to be inputted
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Project2{
    //3D array to save the work in our recursive calls
    private static int [][][] three_string_array;
    //String variables to store our strings from text file
    private static String subsequence_string_1, subsequence_string_2, subsequence_string_3;
    //Scanner object to retrieve the strings from the text file
    private static Scanner scanner;
    //Integers to store string length and 
    private static int i,j,k;


    //Main method to get everything loaded up and started
    public static void main(String [] args){
        //Define scanner object and load in file
        try{
            scanner = new Scanner(new File("input2.txt"));
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
        three_string_array = new int [i+1][j+1][k+1];
        //Take care of the base case
        fill_array_with_zeroes();
        //Run the algorithm
        longest_Common_Substring();
        //Print the result of the longest subsequence
        System.out.print(three_string_array[i][j][k]);
        

    }
    
    
    /**
    Longest Common Subsequence Algorithm
    Three for loops each traversing the array
    If the characters all match up on some position in all three strings then we add 1 plus the value of the diagnolized place in the current position of the array
    Else just input the max of the i-1, j-1, or k-1 position in the current spot of the array
     */
    public static void longest_Common_Substring(){
        for(int m = 1; m < i+1; m++){
            for(int n = 1; n < j+1; n++){
                for(int o = 1; o < k+1; o++){
                    if(subsequence_string_1.charAt(m-1) == subsequence_string_2.charAt(n-1) &&
                            subsequence_string_2.charAt(n-1) == subsequence_string_3.charAt(o-1)){
                        three_string_array[m][n][o] = 1 + three_string_array[m-1][n-1][o-1];
                    }else{
                        three_string_array[m][n][o] = Math.max(Math.max(three_string_array[m-1][n][o], three_string_array[m][n-1][o]),three_string_array[m][n][o-1]);
                    }
                }
            }
        }

    }
    /**
     * Fill the array with zeroes for the base case
     * BASE CASE: If any of the strings are length 0, then put a zero in the array location
     */
    public static void fill_array_with_zeroes(){
        for(int m = 0; m < i; m++){
            for(int n = 0; n < j; n++){
                for(int o = 0; o < k; o++){
                    if(m == 0 || n == 0 || o == 0 ) {
                        three_string_array[m][n][o] = 0;
                    }
                }
            }
        }

    }
}