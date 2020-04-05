package com.cgx.middleware.extension.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Extension {

    String bizCode() default "";

    String name() default "";

    String description() default "";

}
