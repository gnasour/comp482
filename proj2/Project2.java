import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Project2{
    //3D array to save the work in our recursive calls
    private static int [][][] three_string_array;
    private static String subsequence_string_1, subsequence_string_2, subsequence_string_3;
    private static Scanner scanner;
    private static int i,j,k,l;


    //Main
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
        fill_array_with_zeroes();
        least_Common_Substring();
        System.out.print(three_string_array[i][j][k]);

    }
    
    
    
    public static void least_Common_Substring(){
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