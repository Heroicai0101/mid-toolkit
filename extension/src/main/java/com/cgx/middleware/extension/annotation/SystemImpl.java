package com.cgx.middleware.extension.annotation;

import com.cgx.middleware.extension.model.IExtensionPoint;

import java.lang.annotation.*;

/**
 * 系统实现的各业务通用扩展点
 */
@Extension
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SystemImpl {

    String bizCode() default IExtensionPoint.SYSTEM_BIZ_CODE;

    String name() default "";

    String description() default "";

}
