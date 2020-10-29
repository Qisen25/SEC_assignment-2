package SEC2;

import java.util.*;

import java.nio.file.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

import org.python.core.*;
import org.python.util.*;

import api.*;
import csv_plugin.CSVPlugin;
import javaNative.NativeProgressPlugin;
import progress_plugin.ProgressPlugin;

import java.io.*;


public class App 
{
    /**
     * Create plugin controller which enables plugins to access allowed 
     * app resources such as the expression and x values
     */
    private static class myPluginController extends PluginController
    {
        private List<ResultListener> obs = new ArrayList<>();

        @Override
        public void addResultListener(ResultListener resultCb)
        {
            obs.add(resultCb);
        }

        @Override
        public void notifyResultListeners(double currInc, double yResult)
        {
            for(ResultListener p : this.obs)
            {
                p.resultUpdate(currInc, yResult);
            }
        }
    }

    // our fields
    private List<Plugin> pluginList = new ArrayList<>();
    private PluginController plugCtrl = new myPluginController();
    private String expPrompt = "\nEnter a math expression (unknown variables are denoted as x)" + 
                            "\nExpression:> ";

    /**
     * Entry point to be called by main
     */
    public void run() 
    {
        String op = "";
        System.out.println("Native lib path: " + System.getProperty("java.library.path"));
        do
        {
            op = getStringInput("==SEC Assignment 2==\n1). Plugins\n2). Enter expression\n0). Exit\nchoice:> ");
            if (op.equals("1"))
            {
                pluginMenu();
            }
            else if (op.equals("2"))
            {
                expressionMenu();
            }
        } while(!op.equalsIgnoreCase("0"));
    }

    // plugin menu
    private void pluginMenu()
    {
        System.out.println("++ Plugin Menu ++");
        String option = getStringInput("1). Add plugin\n2). View plugins\n0). Back\nchoice:> ");

        if (option.equals("1"))
        {
            addPlugins();
        }
        else if (option.equals("2"))
        {
            printPlugins();
        }
    }

    // Add plugins
    private void addPlugins()
    {
        try
        {
            String pluginName = getStringInput("Enter plugin name:> ");

            Class<?> cls= Class.forName(pluginName);

            Plugin plug = (Plugin)cls.newInstance();
            plug.start(plugCtrl); // load plugin
            
            // Add plugin to list
            this.pluginList.add(plug);

            System.out.println("Plugin: " + pluginName + " has loaded successfully");
        }
        catch (RuntimeException e)
        {
            System.out.println("[ Runtime Exception: loading plugin at during runtime failed: " + e.getMessage() + " ]");
        }
        catch (ReflectiveOperationException e)
        {
            System.out.println("[ Reflective Operation Exception: invalid plugin " + e.getMessage() + " ]");
        }
    }

    // View plugins
    private void printPlugins()
    {
        System.out.println("==Current Loaded Plugins==");
        for(Plugin p : this.pluginList)
        {
            System.out.println("- " + p.getClass().getName());
        }
    }

    // Mount plugins (Used when evaluating expression)
    private void mountMathPlugins(PythonInterpreter py)
    {
        for(Plugin p : this.pluginList)
        {
            this.loadMathPlugin(p.getClass(), py);
        }
    }

    /**
     * Load and import math plugin functions into python interpreter
     * @param cls
     */
    private void loadMathPlugin(Class<?> cls, PythonInterpreter py)
    {
        Annotation anno = cls.getAnnotation(MathAnnotation.class);

        if(anno != null)
        {
            System.out.println("Math plugins found!");
            for(Method m : cls.getMethods())
            {
                Annotation a = m.getAnnotation(MathAnnotation.class);

                if(Modifier.isStatic(m.getModifiers()) && a != null)
                {
                    String importStr = "from " + cls.getName() + " import " + m.getName();
                    System.out.println(importStr);
                    py.exec(importStr);
                }
            }
        }
    }

    /**
     * Get expression values from the user
     */
    private void expressionMenu()
    {
        double minX, maxX, incX;
        String expression;

        // Get equation
        expression = getStringInput(expPrompt);
        System.out.println(expression);
        if (!expression.isEmpty())
        {
            minX = getXValues("Enter a minimum x value:> ");
            maxX = getXValues("Enter a maximum x value:> ");
            incX = getXValues("Enter an increment value:> ");

            plugCtrl.setExpression(expression, minX, maxX, incX);
            pyEvaluate(expression, minX, maxX, incX);
        }
    }

    /**
     *  Evaluate expression via python interpreter
     * @param exp
     * @param min
     * @param max
     * @param inc
     */
    private void pyEvaluate(String exp, double min, double max, double inc)
    {
        try
        {
            PythonInterpreter py = new PythonInterpreter();
            this.mountMathPlugins(py);

            for(double x = min; x <= max; x += inc)
            {
                String subExp = exp.replaceAll("x", String.valueOf(x));
                double result = ((PyFloat) py.eval("float(" + subExp + ")")).getValue();
                plugCtrl.notifyResultListeners(x, result);// notifty result listeners
                System.out.println(result);
            }
        }
        catch(Exception e)
        {
            System.out.println("[ Error evaluating expression (Most likely math func not imported) ]" + e.getMessage());
        }
    }

    /**
     * Get user string input
     */
    private String getStringInput(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        String input;
        
        System.out.print(prompt);
        
        input = sc.nextLine();

        return input;
    }

    /**
     * get Real number user input
     */
    private double getXValues(String prompt)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print(prompt);

        double input = sc.nextDouble();

        return input;
    }
}
