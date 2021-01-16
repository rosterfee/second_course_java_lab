package ru.itis.javaLab;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface HtmlInputAnnotation {

    String type() default "text";
    String name() default "";
    String placeholder() default "";

}
