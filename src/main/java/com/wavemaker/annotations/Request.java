package com.wavemaker.annotations;


import com.wavemaker.framework.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Request {
    String name() default "";

    String path() default "";

    RequestMethod method() default RequestMethod.GET ;
}
