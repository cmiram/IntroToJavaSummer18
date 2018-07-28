import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;

/***
 * This class reads in the data written out by DataPreProcessor. It calculates
 * the percentage of child poverty and formats the results for the report.
 */

public class OutputReport
{
    // common formatter to make table even
    private final static String TABLE_FORMAT = "%5s  %10s  %16s  %23s  %15s";

    // table headers
    private final static Object[] TABLE_HEADERS = new String[] {"State",
            "Population", "Child Population", "Child Poverty Population",
            "% Child Poverty"};

    // table underline
    private final static Object[] TABLE_UNDERLINE = new String[]{"-----",
            "----------", "----------------", "-----------------------",
            "---------------"};

    public static void main(String[] args)
    {
        int recordCnt;
        try
        {
            // parse numeric arg from string
            recordCnt = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            return;
        }

        // write out the report
        outputReport(args[0], recordCnt);
    }

    private static void outputReport(String srcPath, int recordCnt)
    {
        // write out header for the report
        System.out.println("File: " + srcPath + "\n");
        System.out.println(String.format(TABLE_FORMAT, TABLE_HEADERS));
        System.out.println(String.format(TABLE_FORMAT, TABLE_UNDERLINE));
        try
        {
            // open up file and readers
            File srcFile = new File(srcPath);
            FileReader reader = new FileReader(srcFile);
            BufferedReader buffReader = new BufferedReader(reader);
            // var to hold line read in by buffReader
            String line;
            // read file line by line
            while((line = buffReader.readLine()) != null)
            {
                // format the values read in and write out to the report
                Object[] values = processLineForReport(line);
                System.out.println(String.format(TABLE_FORMAT, values));
            }
            // close readers
            buffReader.close();
            reader.close();
        }
        catch(NullPointerException | IOException | NumberFormatException e)
        {
            // print stack trace no return needed since its end of function
            e.printStackTrace();
        }
    }

    private static String[] processLineForReport(String line)
    {
        // split the line by spaces
        String[] values = line.split("\\s+");
        // calculate the poverty percentage
        double poverty = (Double.valueOf(values[3]) /
                Double.valueOf(values[2])) * 100;
        // format percentage to two decimal places
        String povertyStr = String.format("%.2f", poverty);
        // add commas to population totals
        values[1] = NumberFormat.getIntegerInstance()
                .format(Integer.valueOf(values[1]));
        values[2] = NumberFormat.getIntegerInstance()
                .format(Integer.valueOf(values[2]));
        values[3] = NumberFormat.getIntegerInstance()
                .format(Integer.valueOf(values[3]));
        return new String[]{values[0], values[1], values[2],
                values[3], povertyStr};
    }
}
