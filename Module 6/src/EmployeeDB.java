/*
 This class is the entry point for our employee db. It prompts users to enter
 employee information and writes it to console after program completion.
 */

import java.util.Scanner;

public class EmployeeDB {

    // scanner to read input from users
    private static Scanner input;

    public static void main(String[] args)
    {
        input = new Scanner(System.in);
        // set employees to enter to -1 initially so we enter the loop
        int numEmployees = -1;
        // loop until user enters a positive number of employees
        while (numEmployees < 0)
        {
            System.out.print("How many employees would you like to enter? ");
            numEmployees = input.nextInt();
            if (numEmployees < 0)
            {
                System.out.println("Employees to enter must be positive");
            }
        }

        // new employee placeholder object and our db array of employees
        Employee newEmployee;
        Employee[] employees = new Employee[numEmployees];
        // create new employee objects and add them to the array
        for(int i=0; i<numEmployees; i++)
        {
            newEmployee = createNewEmployee();
            employees[i] = newEmployee;
        }

        // for each employee created write out their information
        for (Employee employee : employees)
        {
            System.out.println("\n");
            System.out.println(employee.toString());
        }

        input.close();
    }

    private static Employee createNewEmployee( )
    {
        System.out.print("Enter employee id number: ");
        int employeeNumber = input.nextInt();

        // create objects that are employee object's fields
        Name name = createNewName();
        Address address = createNewAddress();
        Date hireDate = createNewDate();

        return new Employee(employeeNumber, name, address, hireDate);
    }

    private static Name createNewName()
    {
        System.out.print("Enter employees first name: ");
        String firstName = input.next();

        System.out.print("Enter employees last name: ");
        String lastName = input.next();

        return new Name(firstName, lastName);
    }

    private static Address createNewAddress()
    {
        System.out.print("Enter employee's street name: ");
        String street = input.next();

        System.out.print("Enter employee's city: ");
        String city = input.next();

        // ensure state is a 2 character abbreviation
        String state = "";
        while(state.length() != 2)
        {
            System.out.print("Enter employee's state abbreviation: ");
            state = input.next();
            if(state.length() != 2)
            {
                System.out.println("Invalid state abbreviation");
            }
        }

        // ensure zip code is 5 digits long
        String zipCode = "";
        while (zipCode.length() != 5)
        {
            System.out.print("Enter employee's zip code: ");
            zipCode = input.next();
            if(zipCode.length() != 5)
            {
                System.out.println("Invalid zip code");
            }
        }


        return new Address(street, city, state, zipCode);
    }

    private static Date createNewDate()
    {
        // ensure month is 1-12
        int month = -1;
        while(month < 1 || month > 12)
        {
            System.out.print("Enter month employee started: ");
            month = input.nextInt();
            if(month < 1 || month > 12)
            {
                System.out.println("Invalid month value");
            }
        }

        // ensure day is 1-31
        int day = -1;
        while(day < 1 || day > 31)
        {
            System.out.print("Enter day employee started: ");
            day = input.nextInt();
            if(day < 1 || day > 31)
            {
                System.out.println("Invalid day of month value");
            }
        }

        // ensure year is 1901-2019
        int year = -1;
        while(year < 1901 || year > 2019)
        {
            System.out.print("Enter year employee started: ");
            year = input.nextInt();
            if(year < 1901 || year > 2019)
            {
                System.out.println("Invalid start year");
            }
        }

        return new Date(month, day, year);
    }

}
