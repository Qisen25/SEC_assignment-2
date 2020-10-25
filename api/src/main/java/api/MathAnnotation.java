package api;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface MathAnnotation
{
    String name();
}
