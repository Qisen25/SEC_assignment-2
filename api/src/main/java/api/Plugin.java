/**
 * The interface that all plugin should inherit
 */
package api;

public interface Plugin
{
    // Setup plugin and connect to API plugin controller.
    // Plugins implementing this method will be able to
    // access app's expression, min, max and increment via public accessors 
    // of the plugin controller.
    void start(PluginController plugControl);
}
