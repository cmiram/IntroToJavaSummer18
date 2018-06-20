import java.util.Scanner;

/***
 * This class defines functionality to display a calendar of a month on the
 * command line determined by a user prompt of a month and year.
 */

public class DisplayCalendarYear {
    
    // format for output so all calendar lines will be aligned from a basis
    private static String WEEK_FORMATTER = "%3s  %3s  %3s  %3s  %3s  %3s  %3s";
    
    public static void main(String[] args) {
        // instantiate vars for user input
        int year;
        
        // Use a Scanner to input integer values
        Scanner input = new Scanner(System.in);
        
        // loop until user enters valid year
        System.out.print("Enter the year to display: ");
        do {
            year = input.nextInt();
            if(year < 0) {
                System.out.println("Invalid year value");
                System.out.print("Please enter a positive integer: ");
            }
        } while(year < 0);
        
        for(int month=1; month<=12; month++) {
            // print calendar based on user input
            printMonthCalendar(month, year);
            System.out.print("\n");
        }
    }
    
    /**
     * Prints out calendar of given month and year to console.
     *
     * @param month chosen month must be int from 1-12
     * @param year chosen year must be non-negative integer
     */
    private static void printMonthCalendar(int month, int year) {
        //print header and month strings to console
        printMonthHeader(month, year);
        printMonthBody(month, year);
    }
    
    /***
     * Prints out the header info for a calendar.
     *
     * @param month given month of integer 1-12
     * @param year given year of non-negative integer
     */
    private static void printMonthHeader(int month, int year) {
        // build first line of header
        String monthYearHeader = buildMonthYearHeader(month, year);
        
        // build line of hyphens as a divider same length as other lines
        String divider = new String(new char[WEEK_FORMATTER.length()])
                .replace("\0", "-");
        
        // weekdays is just a constant string separated by 2 spaces
        String weekdays = String.format(WEEK_FORMATTER,
                "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
        
        // combine and print out calendar header separated by new lines
        System.out.println(monthYearHeader + "\n" + divider + "\n" + weekdays);
    }
    
    /***
     * Builds a string with a month string and year with centered whitespace.
     *
     * @param month integer of month to get string for
     * @param year non-negative integer to write on line
     *
     * @return whitespace centered string of form "    MONTH  YEAR"
     */
    private static String buildMonthYearHeader(int month, int year) {
        String monthStr = getMonthName(month);
        String header = monthStr + "  "  + Integer.toString(year);
        
        // center title by offsetting half the unused space in a line
        int offsetLength = (WEEK_FORMATTER.length() - header.length()) / 2;
        String offset = new String(new char[offsetLength])
                .replace("\0", " ");
        return offset + header;
    }
    
    /***
     * prints the days of the month by week on the console
     *
     * @param month integer between 1-12
     * @param year non-negative integer
     */
    private static void printMonthBody(int month, int year) {
        Object[] weekFormatOptions = {"", "", "", "", "", "", ""};
        int startDay = getStartDay(month, year);
        int daysInMonth = getNumDaysInMonth(month, year);
        
        int day = 0;
        startDay = startDay == 7 ? 0 : startDay;
        while(day < daysInMonth) {
            for(int dayOfWeek=startDay; dayOfWeek<7; dayOfWeek++) {
                weekFormatOptions[dayOfWeek] = day++ < daysInMonth ?  day : "";
            }
            startDay = 0;
            System.out.println(String.format(WEEK_FORMATTER, weekFormatOptions));
        }
    }
    
    /***
     * Returns the name of a month given its corresponding int value
     *
     * @param month integer of 1-12
     *
     * @return string of the corresponding month int
     */
    private static String getMonthName(int month) {
        final String[] months = {"January", "February", "March", "April", "May",
                "June", "July", "August", "Semptember", "October", "November", "December"};
        return months[month-1];
    }
    
    /***
     * Gets the start day of the week for a given month and year
     * conforming to ISO standards
     *
     * @param month integer of month to find
     * @param year non-negative integer of year to find
     *
     * @return integer of 1-7 corresponding to Mon-Sun respectively
     */
    private static int getStartDay(int month, int year) {
        final int day = 1; // Must be set to day 1 for this to work.  JRD.
        
        // Adjust month number & year to fit Zeller's numbering system
        if ( month < 3 )
        {
            month = month + 12;
            year = year - 1;
        }
        
        int yearInCent = year % 100;      // k Calculate year within century
        int century = year / 100;      // j Calculate century term
        int firstDay;            // h Day number of first day in month 'm'
        
        firstDay = (day + (13 * (month + 1) / 5) + yearInCent +
                (yearInCent / 4) + (century / 4) + (5 * century)) % 7;
        
        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        return ((firstDay + 5) % 7) + 1;
    }
    
    /***
     * Gets the number of days in a given month and year
     *
     * @param month integer of 1-12 corresponding to a month
     * @param year non-negative integer of what year to determine days from
     *
     * @return days in the month taking into account leap years
     */
    private static int getNumDaysInMonth(int month, int year) {
        // array of days per month
        final int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // if february need to check for leap year
        if(month == 2) {
            return isLeapYear(year) ? daysPerMonth[month-1] + 1 : daysPerMonth[month-1];
        }
        
        // grab months per day. minus 1 because zero based array
        return daysPerMonth[month-1];
    }
    
    /***
     * Determines if a given year is a leap year according to the
     * Gregorian calendar
     *
     * @param year non-negative integer to determine leap year status
     *
     * @return whether input is leap year or not
     */
    private static boolean isLeapYear(int year) {
        // leap year if divisible by 4 but not 100
        // if divisible by 4 and 100 only leap year if divisible by 400
        // short-circuiting first boolean expr ensures correct result
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
}
