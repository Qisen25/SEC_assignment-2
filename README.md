# SEC Assignment 2 by Kei Wang 19126089

## Plugins
These are the names of included plugins and how they should be typed when entering plugins.

- Java Progress indicator : progress_plugin.ProgressPlugin
- CSV Plugin : csv_plugin.CSVPlugin
- C/C++ progress indicator : javaNative.NativeProgressPlugin
- Math functions: math_plugin.MathPlugin

You will need to know the name of your package/code if you make your own.



## NOTE
If you executing app from zip file in build/distributions you will need
to " export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$HOME/Downloads " in the terminal for the native plugin to work.

You may need to set export JAVA_HOME=/usr/lib/jvm/... if gradle says jni.h cannot be found
