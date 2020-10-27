#include <iostream>
#include <string>
#include <stdlib.h>
#include <jni.h>


extern "C" 
{
    JNIEXPORT void JNICALL Java_javaNative_StaticMethods_printProgress(JNIEnv *env, jclass cls, jdouble curr, jdouble end)
    {
        int percent = (int)((curr / end) * 100);
        std::string progress = "[ Native Progress Indicator..." + std::to_string(percent) + " % ]";
        std::cout << "\r" << progress << std::flush;
        if(percent >= 100.0)
        {
            std::cout << std::endl;
        }
    }
}
