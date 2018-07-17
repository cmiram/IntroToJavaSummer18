/***
 * Subclass of Employee who's pay is is fixed for the first 40 hours
 * and at a 1.5x rate for hours over 40.
 */

public class HourlyEmployee extends Employee
{
    private double hourlyRate;

    private double hoursWorked;

    private double earnings;

    public HourlyEmployee(int employeeNumber, Name name, Address address,
                          Date hireDate, double hourlyRate, double hoursWorked,
                          double earnings)
    {
        super(employeeNumber, name, address, hireDate);

        if(hourlyRate < 0 || hoursWorked < 0 || earnings < 0)
        {
            throw new IllegalArgumentException("Rates and earnings cannot " +
                    "be negative");
        }

        if(hoursWorked > 168)
        {
            throw new IllegalArgumentException("Cannot work more hours than " +
                    "there are in a week");
        }

        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.earnings = earnings;
    }

    public double calculateWeeklyPay()
    {
        double pay = hoursWorked <= 40 ? hoursWorked * 40 :
                (hoursWorked * 40) + ((hoursWorked-40) * 1.5 * hoursWorked);
        earnings += pay;
        return pay;
    }

    public double getHourlyRate()
    {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate)
    {
        if(hourlyRate < 0)
        {
            throw new IllegalArgumentException("Cannot be negative");
        }

        this.hourlyRate = hourlyRate;
    }

    public double getHoursWorked()
    {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked)
    {
        if(hoursWorked < 0)
        {
            throw new IllegalArgumentException("Cannot be negative");
        }

        if(hoursWorked > 168)
        {
            throw new IllegalArgumentException("Cannot work more hours " +
                    "than there are in a week");
        }

        this.hoursWorked = hoursWorked;
    }

    public double getEarnings()
    {
        return earnings;
    }

    public void setEarnings(double earnings)
    {
        if(earnings < 0)
        {
            throw new IllegalArgumentException("Cannot be negative");
        }

        this.earnings = earnings;
    }

    @Override
    public String toString()
    {
        final String employeeStr = "Employee Number: %d\nName: %s\nAddress:\n%s\n" +
                "Hire Date: %s\nPay Rate: %s\nHours Worked: %s\n" +
                "Earnings: %s\nWeeklyPay: %s";
        return String.format(employeeStr, getEmployeeNumber(),
                getName().toString(), getAddress().toString(),
                getHireDate().toString(), Double.toString(hourlyRate),
                Double.toString(hoursWorked), Double.toString(earnings),
                Double.toString(calculateWeeklyPay()));
    }
}
