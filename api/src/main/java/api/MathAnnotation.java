package api;

import java.lang.annotation.*;

/**
 * All math plugins should use this annotation on functions
 * to allow app to identify which functions are math functions.
 * 
 * The Reason for using annotations is to allow for versatility
 * Sometimes a plugin may not fully be a math plugin but may contain math
 * stuff that could be used, thus no need to create/implement math interface
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MathAnnotation
{
    String name();
}
