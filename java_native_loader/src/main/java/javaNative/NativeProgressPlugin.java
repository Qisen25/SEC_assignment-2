package javaNative;

import api.*;

public class NativeProgressPlugin implements Plugin
{
    private final class ProgressCallback implements ResultListener
    {
        @Override
        public void resultUpdate(double currInc, double yResult)
        {
            //System.load("/home/beep-beep/libnative_plugin.so");
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
        plugCtrl.addResultListener(pc);
        // StaticMethods.printProgress();
    }
}
