/**
 *  This program takes in 6 integers and calculates a series of sums
 *  which are then displayed to the user.
 *
 *  The sums calculated are of the following where in1..6 are the user's:
 *
 *  in1 | in2 | in1+in2
 *  in3 | in4 | in3+in4
 *  in5 | in6 | in5+in6
 *  ---   ---   ---
 *  all column totals
**/

import java.util.Scanner;

public class Assignment1_Solution
{
    public static void main( String [] args )
    {
        // Define and initialize variables for values to be input
        int userNum1 = 0;
        int userNum2 = 0;
        int userNum3 = 0;
        int userNum4 = 0;
        int userNum5 = 0;
        int userNum6 = 0;
        
        // Use a Scanner to input integer values
        Scanner input = new Scanner( System.in );
        System.out.println( "\n\n" );
        System.out.print( "Enter 6 integers separated by a blank space:" );
        userNum1 = input.nextInt();     // Input first value
        userNum2 = input.nextInt();     // Input second value
        userNum3 = input.nextInt();     // Input third value
        userNum4 = input.nextInt();     // Input fourth value
        userNum5 = input.nextInt();     // Input fifth value
        userNum6 = input.nextInt();     // Input sixth value
        input.close();
        
        // use string formatting to ensure string and int columns align
        String numberRowFormat = "\t\t%-5d\t%-5d\t%-5d";
        String stringRowFormat = "\t\t%-5s\t%-5s\t%-5s";
        
        // Display the table.
        System.out.println("\n\n");
        // header with table column names
        System.out.println(String.format(stringRowFormat, "Value", "Value",
                "Total"));
        // use common format for each row with only numbers
        System.out.println(String.format(numberRowFormat, userNum1, userNum2,
                userNum1+userNum2));
        System.out.println(String.format(numberRowFormat, userNum3, userNum4,
                userNum3+userNum4));
        System.out.println(String.format(numberRowFormat, userNum5, userNum6,
                userNum5+userNum6));
        //create bottom row totals
        System.out.println("\t\t----\t----\t----");
        //first=inputs1+3+5 - second=inputs2+4+6 - last=sum of all
        System.out.println(String.format("Total\t%-5d\t%-5d\t%-5d",
                userNum1+userNum3+userNum5,
                userNum2+userNum4+userNum6,
                userNum1+userNum2+userNum3+userNum4+userNum5+userNum6));
        System.out.println("\n\n");
    }
}
