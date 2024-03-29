/*
 * This C++ source file was generated by the Gradle 'init' task.
 */
#ifndef NATIVE_PLUGIN_H
#define NATIVE_PLUGIN_H

#ifdef _WIN32
#define NATIVE_PLUGIN_EXPORT_FUNC __declspec(dllexport)
#else
#define NATIVE_PLUGIN_EXPORT_FUNC
#endif

#include <string>

namespace native_plugin {
    class Greeter {
        public:
        std::string NATIVE_PLUGIN_EXPORT_FUNC greeting();
    };
}

#endif
