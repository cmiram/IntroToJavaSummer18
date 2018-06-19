import java.text.DateFormatSymbols;
import java.util.Scanner;

/***
 * This class defines functionality to display a calendar of a month on the
 * command line determined by a user prompt of a month and year.
 */

public class DisplayMonthFromDate {

    // for alignment. 7 days in week with 3 chars per -2 b/c Sat ends line
    private final static int CHARS_PER_CALENDAR_LINE = (5 * 7) - 2;

    public static void main(String[] args) {
        // instantiate vars with negative values for loop control
        int month = -1;
        int year = -1;

        // Use a Scanner to input integer values
        Scanner input = new Scanner(System.in);

        // loop until user enters a valid month input
        System.out.print("Enter the month to display: ");
        do {
            month = input.nextInt();
            if(month < 0 || month > 12) {
                System.out.println("Invalid month value");
                System.out.print("Please enter a value in range of 1-12: ");
            }
        } while(month < 0 || month > 12);

        // loop until user enters valid year
        System.out.print("Enter the year to display: ");
        do {
            year = input.nextInt();
            if(year < 0) {
                System.out.println("Invalid year value");
                System.out.print("Please enter a positive integer: ");
            }
        } while(year < 0);

        // print calendar based on user input
        printMonthCalendar(month, year);
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

        // build line of hyphens as a divider
        StringBuilder divider = new StringBuilder(CHARS_PER_CALENDAR_LINE);
        for(int i=0; i<CHARS_PER_CALENDAR_LINE; i++) {
            divider.append("-");
        }

        // weekdays is just a constant string separated by 2 spaces
        String weekdays = "Sun  Mon  Tue  Wed  Thu  Fri  Sat";

        // combine and print out calendar header separated by new lines
        System.out.println(monthYearHeader + "\n" + divider.toString() + "\n" + weekdays);
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
        int offsetLength = (CHARS_PER_CALENDAR_LINE - header.length()) / 2;
        StringBuilder offset = new StringBuilder(offsetLength);
        for(int i=0; i<offsetLength; i++) {
            offset.append(" ");
        }
        return offset.toString() + header;
    }

    /***
     * prints the days of the month by week on the console
     *
     * @param month integer between 1-12
     * @param year non-negative integer
     */
    private static void printMonthBody(int month, int year) {
        // each day on calendar is 3 chars wide so format for easy alignment
        String dayFormat = "%3d";
        StringBuilder monthStr = new StringBuilder();

        // count days written to determine when to add new line
        int daysWritten = 0;
        int startDay = getStartDay(month, year);

        // since iso's Sunday=7 don't add whitespace on that case
        // otherwise add 5 spaces per empty day before 1st of the month
        while(startDay != 7 && startDay-- > 0) {
            daysWritten++;
            monthStr.append("     ");
        }

        // start day at 0 because loop increments to 1 before writing day
        int day = 0;
        // iterate individual days until reaching total days in month
        while(day++ <= getNumDaysInMonth(month, year)) {
            monthStr.append(String.format(dayFormat, day));
            daysWritten++;
            if(daysWritten == 7) {
                monthStr.append("\n"); // new line for new week
                daysWritten = 0;
            }
            else {
                monthStr.append("  "); // 2 spaces between each day on console
            }
        }
        System.out.print(monthStr.toString());
    }

    /***
     * Returns the name of a month given its corresponding int value
     *
     * @param month integer of 1-12
     *
     * @return string of the corresponding month int
     */
    private static String getMonthName(int month) {
        // get month string -1 because its zero-indexed
        return DateFormatSymbols.getInstance().getMonths()[month-1];
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
