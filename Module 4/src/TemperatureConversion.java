/***
 * This program prompts the user to enter a temperature scale and converts
 * between Fahrenheit and Celcius depending on the input.
 */

import java.util.Scanner;

public class TemperatureConversion
{
    public static void main( String [] args )
    {
        int userChoice = 0; // User selection: 1, 2, 3
        Scanner input = new Scanner(System.in); // Create a Scanner to obtain user input
        
        while( userChoice != 3 )
        {
            System.out.print( "Enter 1 to convert F->C, 2 to convert C->F, 3 to quit: " );
            userChoice = input.nextInt(); // Read user input
            
            if(validConversionInput(userChoice)) {
                System.out.print("Enter your temperature: ");
                float userTemp = input.nextFloat();
                printTemps(userTemp, convertTemp(userTemp, userChoice), userChoice);
            }
            else if (userChoice != 3) {
                System.out.println( "Invalid Data: You must enter 1, 2, or 3" );
            }
        }
        System.out.println("Bye Bye");
    }

    /***
     * Determines if input was a valid choice for running a conversion
     *
     * @param userChoice option chosen by user
     *
     * @return True if 1 (Fahrenheit) or 2 (Celsius) otherwise false
     */
    private static boolean validConversionInput(int userChoice) {
        return userChoice == 1 || userChoice == 2;
    }
    
    /***
     * Converts a temperature between Fahrenheit and Celcius based on user selected input.
     *
     * @param temp floating point value of user defined temp
     * @param userChoice int where 1 is Fahrenheit and 2 is Celcius
     *
     * @return floating point value of the converted temperature
     */
    private static float convertTemp(float temp, int userChoice) {
        return userChoice == 1 ? 5F/9F * (temp - 32F) : (9F/5F * temp) + 32;
    }
    
    /***
     * Prints out input and converted temperatures with input one first.
     *
     * @param userTemp temperature input by the user
     * @param userChoice int where 1 is Fahrenheit input and 2 is Celcius input
     */
    private static void printTemps(float userTemp, float convertedTemp, int userChoice) {
        String outputFormat = String.format("%.2f degrees %s is %.2f degrees %s", userTemp, "%s", convertedTemp, "%s");
        Object[] tempScales = userChoice == 1 ? new String[]{"Fahrenheit", "Celcius"} : new String[]{"Celcius", "Fahrenheit"};
        System.out.println(String.format(outputFormat, tempScales));
    }
}