/**
 * This is the control api to be implemented by the main application
 * Controls or gives plugins access to app resource
 */
package api;

import java.util.ArrayList;
import java.util.List;

public abstract class PluginController
{
    private String exp;
    private double min;
    private double max;
    private double inc;

    // Abstract api methods registering and notifying result listeners (Listen y and x result updates)
    public abstract void addResultListener(ResultListener plugin);
    public abstract void notifyResultListeners(double currInc, double yResult);

    public void setExpression(String exp, double min, double max, double inc)
    {
        this.exp = exp;
        this.min = min;
        this.max = max;
        this.inc = inc;
    }

    // Getters to allow plugins to access the expression, min, max and increment
    public String getExp() {
        return this.exp;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public double getInc() {
        return this.inc;
    }
}
