package javaNative;

public class StaticMethods
{
    static 
    {
        System.load("/home/beep-beep/libnative_plugin.so");
        //System.load("native_plugin");
    }

    public native static void printProgress(double curr, double end);
}
