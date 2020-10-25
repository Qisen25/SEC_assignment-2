package math_plugin;

import api.*;

@MathAnnotation(name = "Factorial")
public class FactorialPlugin implements Plugin
{
    private PluginController plugControl = null;

    @Override
    public void start(PluginController plugControl)
    {
        this.plugControl = plugControl;
    }

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
}
