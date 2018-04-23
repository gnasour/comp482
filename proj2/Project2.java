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
        three_string_array = new int [i][j][k];
        least_Common_Substring();
        System.out.print(three_string_array[i][j][k]);

        //System.out.println(l);
    }
    
    //The recursive method that will populate our three string array
     /*public static int least_Common_Substring(int [][][] three_string_array, int tempI, int tempJ, int tempK){
        i = tempI;
        j = tempJ;
        k = tempK;
        if(i == 0 || j == 0 || k == 0){
            return 0;
        }else if(subsequence_string_1.charAt(i-1) == subsequence_string_2.charAt(j-1) && 
            subsequence_string_2.charAt(j-1) == subsequence_string_3.charAt(k-1)){
                i-=1;
                j-=1;
                k-=1;
                return 1 + least_Common_Substring(three_string_array,i,j,k);
        }else { //If one of the characters are not equal then check the max of all the subtractions
                return Math.max(
                    Math.max(least_Common_Substring(three_string_array,i-1,j,k),least_Common_Substring(three_string_array,i,j-1,k))
                    , least_Common_Substring(three_string_array,i,j,k-1));
            }

    }*/

    public static void least_Common_Substring(){
        for(int m = 0; m <= i; m++){
            for(int n = 0; n <= j; n++){
                for(int o = 0; o <= k; o++){
                    if(m == 0 || n == 0 || o == 0 )
                        three_string_array[i][j][k] = 0;
                    else if(subsequence_string_1.charAt(m-1) == subsequence_string_2.charAt(n-1) &&
                            subsequence_string_2.charAt(n-1) == subsequence_string_3.charAt(o-1)){
                        three_string_array[m][n][o] = 1 + three_string_array[m-1][n-1][o-1];
                    }else{
                        three_string_array[m][n][o] = Math.max(Math.max(three_string_array[m-1][n][o], three_string_array[m][n-1][o]),three_string_array[m][n][o-1]);
                    }
                }
            }
        }

    }
}