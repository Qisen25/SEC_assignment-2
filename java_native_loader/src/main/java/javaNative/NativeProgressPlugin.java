package javaNative;

import api.*;

/**
 * Native progress indicator plugin
 */
public class NativeProgressPlugin implements Plugin
{
    private final class ProgressCallback implements ResultListener
    {
        @Override
        public void resultUpdate(double currInc, double yResult)
        {
            // Call C++ code
            StaticMethods.printProgress(currInc, currPlugCtrl.getMax());
        }
    }

    private PluginController currPlugCtrl = null;

    @Override
    public void start(PluginController plugCtrl)
    {
        this.currPlugCtrl = plugCtrl;
        ProgressCallback pc = new ProgressCallback();
        plugCtrl.addResultListener(pc);// Add to PluginController's list of listeners
    }
}
