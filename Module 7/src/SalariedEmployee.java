/***
 * Subclass of Employee who's pay is is fixed per year.
 */

public class SalariedEmployee extends Employee
{
    private double salary;

    public SalariedEmployee(int employeeNumber, Name name, Address address,
                            Date hireDate, double salary)
    {
        super(employeeNumber, name, address, hireDate);

        if(salary < 0)
        {
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        this.salary = salary;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        if(salary < 0)
        {
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        this.salary = salary;
    }

    @Override
    public String toString()
    {
        final String employeeStr = "Employee Number: %d\nName: %s\nAddress:\n%s\n" +
                "Hire Date: %s\nSalary: %s";
        return String.format(employeeStr, getEmployeeNumber(),
                getName().toString(), getAddress().toString(),
                getHireDate().toString(), Double.toString(salary));
    }
}
