package javaNative;

/**
 * Class containing static methods used by the MathPlugin
 * 
 * Reason for this is to make it easier to import into python interpreter
 */
public class StaticMethods
{
    static 
    {
        System.loadLibrary("native_plugin");
    }

    // Print current progress
    public native static void printProgress(double curr, double end);
}
