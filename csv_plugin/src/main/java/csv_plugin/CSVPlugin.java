package csv_plugin;

import java.io.*;
import org.apache.commons.csv.*;
import api.*;

/**
 * Plugin to print x and y values to csv file
 * 
 * NOTE: File should be printed to root of project if using ./gradlew run
 */
public class CSVPlugin implements Plugin
{
    private final class ProgressCallback implements ResultListener
    {
        @Override
        public void resultUpdate(double currInc, double yResult)
        {
            // append to txt file
            try(CSVPrinter printer = new CSVPrinter(new FileWriter("csv.txt", true), CSVFormat.EXCEL))
            {
                printer.printRecord(currInc, yResult);
            }
            catch(IOException e)
            {
                System.out.println("Error writing csv " + e.getMessage());
            }
        }
    }    

    @Override
    public void start(PluginController plugControl)
    {
        ProgressCallback pc = new ProgressCallback();
        plugControl.addResultListener(pc);
    }
}
