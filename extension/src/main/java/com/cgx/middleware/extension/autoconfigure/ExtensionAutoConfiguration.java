package com.cgx.middleware.extension.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class ExtensionAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ExtensionAutoConfiguration.Marker.class)
    public Marker marker() {
        return new ExtensionAutoConfiguration.Marker();
    }

    @Bean
    @ConditionalOnBean(ExtensionAutoConfiguration.Marker.class)
    public ExtPointBeanPostProcess extPointBeanPostProcess() {
        return new ExtPointBeanPostProcess();
    }

    /** 标记类, 无实质作用 */
    class Marker {
    }

}
