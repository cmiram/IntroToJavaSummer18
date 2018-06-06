/**
 *  This program takes in a weight and height of the user and
 *  calculates their BMI.
 **/


import java.util.Scanner;

public class BodyMassIndex {
    
    // conversion factor constants
    private static final double KILOS_PER_POUND = 0.45359237;
    private static final double METERS_PER_INCH = 0.0254;
    
    public static void main( String [] args ) {
        // double so they can be unit converted exactly later
        double weight, height;
        
        // use scanner to read in user height and weight
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter height in inches: ");
        height = in.nextDouble();
        System.out.print("Enter weight in pounds: ");
        weight = in.nextDouble();
        
        // convert height and weight into kilograms and meters
        height *= METERS_PER_INCH;
        weight *= KILOS_PER_POUND;
        
        // calculate bmi as (weight in kilos) / (height in meters)^2
        double bmi = weight / (height * height);
        System.out.println("Body Mass Index: " + bmi);
        
        // write out bmi categories
        System.out.println("\n\tUnderweight: less than 18.5");
        System.out.println("\tNormal: 18.5 – 24.9");
        System.out.println("\tOverweight: 25 – 29.9");
        System.out.println("\tObese: 30 or greater");
    }
}
