/***
 * This class represents an employee containing an id number, their name,
 * address, and date they were hired.
 */

public class Employee
{
    private int employeeNumber;

    private Name name;

    private Address address;

    private Date hireDate;

    public Employee(int employeeNumber, Name name,
                    Address address, Date hireDate)
    {
        if(employeeNumber < 1)
        {
            throw new IllegalArgumentException("Employee number must be " +
                    "positive integer");
        }

        this.employeeNumber = employeeNumber;
        this.name = name;
        this.address = address;
        this.hireDate = hireDate;
    }

    public int getEmployeeNumber()
    {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }

    public Name getName()
    {
        return name;
    }

    public void setName(Name name)
    {
        this.name = name;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Date getHireDate()
    {
        return hireDate;
    }

    public void setHireDate(Date hireDate)
    {
        this.hireDate = hireDate;
    }

    @Override
    public String toString()
    {
        final String employeeStr = "Employee Number: %d\nName: %s\nAddress:\n%s\n" +
                "Hire Date: %s";
        return String.format(employeeStr, employeeNumber, name.toString(),
                address.toString(), hireDate.toString());
    }
}
