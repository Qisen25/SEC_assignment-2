package progress_plugin;

import api.*;

public class ProgressPlugin implements Plugin
{
    private final class ProgressCallback implements ResultListener
    {
        @Override
        public void resultUpdate(double currInc, double yResult)
        {
            double percent = (currInc / progressControl.getMax()) * 100.0;
            // System.out.println(progressControl.getMax());
            System.out.print("\r[ Progressing... " + (int)percent + "% ]");
            if(percent >= 100.0)
            {
                System.out.println();
            }
        }
    }

    private PluginController progressControl = null;

    @Override
    public void start(PluginController plugControl)
    {
        this.progressControl = plugControl;
        ProgressCallback pc = new ProgressCallback();
        plugControl.addResultListener(pc);
    }
}
