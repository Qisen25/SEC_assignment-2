package progress_plugin;

import api.*;

/**
 * Java plugin: Progress indicator
 */
public class ProgressPlugin implements Plugin
{
    private final class ProgressCallback implements ResultListener
    {
        @Override
        public void resultUpdate(double currInc, double yResult)
        {
            double percent = (currInc / progressControl.getMax()) * 100.0;
            System.out.print("\r[ Progressing... " + (int)percent + "% ]");
            if(percent >= 100.0)// make sure new line is added at 100 otherwise mess up view
            {
                System.out.println();
            }
        }
    }

    private PluginController progressControl = null; // use to access expression variables

    @Override
    public void start(PluginController plugControl)
    {
        this.progressControl = plugControl;
        // Create call back and add to list of listeners in PluginController
        ProgressCallback pc = new ProgressCallback();
        plugControl.addResultListener(pc);
    }
}
