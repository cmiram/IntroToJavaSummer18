/***
 * This class represents a date. It contains a month, day of month, and year.
 */

public class Date {

    private int month;

    private int day;

    private int year;

    public Date(int month, int day, int year)
    {
        // a month is 1-12 i.e January to December
        if(month < 1 || month > 12)
        {
            throw new IllegalArgumentException("Invalid month value");
        }

        // the limits of days in a month is 1 to 31 at most
        if(day < 1 || day > 31)
        {
            throw new IllegalArgumentException("Invalid day value");
        }

        // a valid hire year is defined as between 1901-2019
        if(year < 1901 || year > 2019)
        {
            throw new IllegalArgumentException("Invalid year value");
        }

        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }
}
