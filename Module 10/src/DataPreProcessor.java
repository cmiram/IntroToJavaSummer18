import java.io.*;

/***
 * This class reads in a file then strips away irrelevant data for our
 * report. The relevant data is combined for each fips code. The collected
 * data is written out to another file.
 */

public class DataPreProcessor
{
    // arg order is source path, destination path, record count
    public static void main(String[] args)
    {
        int recordCnt;
        try
        {
            // parse numeric arg from string
            recordCnt = Integer.parseInt(args[2]);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            return;
        }

        // run pre-processing method
        processDataFile(args[0], args[1], recordCnt);
    }

    private static void processDataFile(String src, String dest, int recordCnt)
    {
        // 50 states plus DC
        final int numUnqCodes = 51;
        String[] fipsCodes = new String[numUnqCodes];
        fipsCodes[0] = "01"; // necessary for while loop
        int[] populations = new int[numUnqCodes];
        int[] childPops = new int[numUnqCodes];
        int[] childPovPops = new int[numUnqCodes];

        int index = 0;
        try
        {
            // open up file and readers
            File srcFile = new File(src);
            FileReader reader = new FileReader(srcFile);
            BufferedReader buffReader = new BufferedReader(reader);
            // var to hold line read in by buffReader
            String line;
            // read file line by line
            while((line = buffReader.readLine()) != null)
            {
                // cleans line and grabs relevant values
                String[] cleaned = cleanLine(line);
                // file fips codes are in order so increment index when loop
                // runs into a new one
                if(!fipsCodes[index].equals(cleaned[0]))
                {
                    index++;
                    fipsCodes[index] = cleaned[0];
                }
                // parse value and add to population total
                populations[index] = populations[index] +
                        Integer.valueOf(cleaned[1]);
                // parse value and add to child population total
                childPops[index] = childPops[index] +
                        Integer.valueOf(cleaned[2]);
                // parse value and add to child poverty population total
                childPovPops[index] = childPovPops[index] +
                        Integer.valueOf(cleaned[3]);
            }
            // close readers
            buffReader.close();
            reader.close();
        }
        catch(NullPointerException | IOException | NumberFormatException e)
        {
            // if run into exception print stack trace and exit because
            // any error will mean results won't be accurate
            e.printStackTrace();
            return;
        }

        try
        {
            // open up destination file and writers
            File destFile = new File(dest);
            FileWriter writer = new FileWriter(destFile);
            BufferedWriter buffWriter = new BufferedWriter(writer);
            // iterate over each unique fips code
            for(int i=0; i<fipsCodes.length; i++)
            {
                // write out fips code and totals
                buffWriter.write(fipsCodes[i] + " " +
                        Integer.toString(populations[i]) + " " +
                        Integer.toString(childPops[i]) + " " +
                        Integer.toString(childPovPops[i]));
                // writes proper new line for OS
                buffWriter.newLine();
            }
            // close out writers
            buffWriter.close();
            writer.close();
        }
        catch(IOException e)
        {
            // print stack trace no return needed since its end of function
            e.printStackTrace();
        }
    }

    // strips irrelevant data and returns array of relevant data
    private static String[] cleanLine(String line)
    {
        // strip off district name b/c some have numbers which mess up regex
        line = line.substring(0, 10) + line.substring(82);
        // remove letters from string since they're irrelevant
        line = line.replaceAll("[^\\d ]", "");
        // split over n sequential spaces
        String[] columns = line.split("\\s+");
        // return array of codes, population, child pop, and poverty pop
        return new String[]{columns[0], columns[2], columns[3], columns[4]};
    }
}
