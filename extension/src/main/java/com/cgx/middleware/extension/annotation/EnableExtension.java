package com.cgx.middleware.extension.annotation;

import com.cgx.middleware.extension.autoconfigure.ExtensionAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用扩展点扫描
 */
@Import(ExtensionAutoConfiguration.class)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EnableExtension {

}
