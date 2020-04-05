package com.cgx.middleware.extension.autoconfigure;

import com.cgx.middleware.extension.ExtensionManager;
import com.cgx.middleware.extension.annotation.Extension;
import com.cgx.middleware.extension.exception.MidFrameworkException;
import com.cgx.middleware.extension.model.ExtensionCoordinate;
import com.cgx.middleware.extension.model.IExtensionPoint;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 扩展点扫描、注册
 */
public class ExtPointBeanPostProcess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 识别扩展点实例, 并完成扩展点注册
     * 1.扩展点必须实现IExtensionPoint接口
     * 2.强制要求扩展点接口名必须以ExtPt结尾
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof IExtensionPoint) {
            IExtensionPoint extPoint = (IExtensionPoint) bean;

            ExtensionCoordinate coordinate = parseExtensionCoordinate(bean.getClass());
            IExtensionPoint oldValue = ExtensionManager.getInstance()
                                                       .registerExtPoint(coordinate, extPoint);
            if (Objects.nonNull(oldValue)) {
                throw new MidFrameworkException("Duplicate registration for :" + coordinate);
            }
        }
        return bean;
    }

    private ExtensionCoordinate parseExtensionCoordinate(Class<?> targetBean) {
        // 扩展点接口
        Optional<Class> extPointClass = checkExtensionPoint(targetBean);
        if (!extPointClass.isPresent()) {
            throw new MidFrameworkException(
                    String.format("The name of ExtensionPoint for %s must be end with %s", targetBean, IExtensionPoint.EXT_POINT_NAMING));
        }

        // 解析@Extension注解
        AnnotationAttributes attrs = AnnotatedElementUtils.getMergedAnnotationAttributes(targetBean, Extension.class);
        String bizCode = attrs.getString("bizCode");
        String name = attrs.getString("name");
        String description = attrs.getString("description");

        return ExtensionCoordinate.builder()
                                  .bizCode(bizCode)
                                  .extensionPoint(extPointClass.get().getName())
                                  .name(name)
                                  .description(description)
                                  .build();
    }

    private Optional<Class> checkExtensionPoint(Class<?> targetBean) {
        Class[] interfaces = targetBean.getInterfaces();
        if (interfaces == null || interfaces.length == 0) {
            throw new MidFrameworkException("No extension point interface for " + targetBean);
        }

        // 扩展点接口名必须以ExtPt结尾
        return Arrays.stream(interfaces)
                     .filter(ext -> ext.getSimpleName().endsWith(IExtensionPoint.EXT_POINT_NAMING))
                     .findFirst();
    }

}