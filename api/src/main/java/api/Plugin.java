/**
 * The interface that all plugin should inherit
 */
package api;

public interface Plugin
{
    // setup plugin and connect to API plugin controller
    void start(PluginController plugControl);
}
