package math_plugin;

import api.*;

/**
 * Math plugin containing math function factorial and fibonacci
 */
@MathAnnotation(name = "Math") // Annotate math to make sure it is a class containing math
public class MathPlugin implements Plugin
{
    private PluginController plugControl = null;

    @Override
    public void start(PluginController plugControl)
    {
        // For this plugin, pluginController doesn't need to be used or accessed
        // However, if any future changes require the controller, it is good to keep here.
        this.plugControl = plugControl;
    }

    // Annotate to make sure that that this function is a math thing
    @MathAnnotation(name = "FactorialFunction")
    public static long factorial(double x)
    {
        long res = 1;
        long max = (long)x;

        if (max < 0)
        {
            return 0;
        }

        for(long i = 2; i <= max; i++)
        {
            res *= i;
        }
        
        return res;
    }

    @MathAnnotation(name = "FibonacciFunction")
    public static long fibonacci(double x)
    {
        long n1 = 0, n2 = 1, n3;
        long max = (long)x;

        if (max < 0)
        {
            return 0;
        }

        for(long i = 2; i <= max; i++)
        {
            n3 = n1 + n2;
            n1 = n2; // shift n1 to n2 value
            n2 = n3; // shift n2 to n3 value
        }
        
        return n2;
    }
}
