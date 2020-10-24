/**
 * This interface is used for callback events whenever a y result has been calculated
 */
package api;

public interface ResultListener
{
    void resultUpdate(double currInc, double yResult);
}