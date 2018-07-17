/***
 * Entry point to test the two paid employee classes
 */

public class PaidEmployeesTest
{

    public static void main(String[] args)
    {
        int employeeNum = 0;
        Name name = new Name("John", "Smith");
        Address address = new Address("10 Baker St.", "Boston",
                "MA", "02115");
        Date date = new Date(7, 10, 2018);

        Employee[] employees = new Employee[3];
        employees[0] = new SalariedEmployee(employeeNum++, name, address,
                date, 50000);

        Name name2 = new Name("Jane", "Doe");
        Address address2 = new Address("20 Java Ct.", "Baltimore",
                "MD", "12345");
        Date date2 = new Date(1, 22, 1990);

        employees[1] = new HourlyEmployee(employeeNum++, name2, address2,
                date2, 15, 10, 0);

        Name name3 = new Name("Vint", "Cerf");
        Address address3 = new Address("IP Ln.", "New Haven",
                "CT", "06501");
        Date date3 = new Date(6, 23, 1943);

        employees[2] = new HourlyEmployee(employeeNum, name3, address3,
                date3, 192, 168, 0.1);

        for(Employee employee : employees)
        {
            System.out.println(employee.toString() + "\n");
        }
    }

}
