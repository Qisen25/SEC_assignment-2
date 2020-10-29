==How to create plugins for this app

When creating your plugin in java, you must implement the Plugin interface.

If your plugin is required to listen to results of any expression evaluation you should 
create an internal callback class containing you custom task that implements ResultListener. This callback should be added to the PluginController result listener list. (PluginController is passed in the Plugin start method that must be implemented).


==Math plugins
To implement math plugins, The class and its functions should be marked with MathAnnotation.
The app uses annotations to identify if the plugin contains math functions. You functions can have whatever name you want.
Annotations give a more versatile approach when creating math plugins. Math specific interfaces will not be required when implementing plugins.

==PluginController
All plugins will have a plugin controller passed into the start function. From there, implemented plugins can access the expression, min, max and increment via public getters.
