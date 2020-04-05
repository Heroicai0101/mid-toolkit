package com.cgx.middleware.extension;

import com.cgx.middleware.extension.exception.MidFrameworkException;
import com.cgx.middleware.extension.model.BizId;
import com.cgx.middleware.extension.model.ExtensionCoordinate;
import com.cgx.middleware.extension.model.IExtensionPoint;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 扩展点实例执行器: 封装扩展点查找策略及方法调用
 */
public class ExtensionExecutor  {

    private ExtensionExecutor() {
        throw new IllegalStateException("No instance");
    }

    /**
     * 有返回值
     *
     * @param targetClass 扩展点类名
     * @param bizId       业务身份
     * @param exeFunction 需要调用的扩展点方法
     */
    public static <R, T> R execute(BizId bizId, Class<T> targetClass, Function<T, R> exeFunction) {
        T component = findComponent(bizId, targetClass);
        return exeFunction.apply(component);
    }

    /**
     * 无返回值
     *
     * @param targetClass 扩展点类名
     * @param bizId       业务身份
     * @param exeConsumer 需要调用的扩展点方法
     */
    public static <T> void consume(BizId bizId, Class<T> targetClass, Consumer<T> exeConsumer) {
        T component = findComponent(bizId, targetClass);
        exeConsumer.accept(component);
    }

    /**
     * 精确查找扩展点: 先看是否有业务实现的扩展点；若没有，再看是否有平台默认实现
     */
    @SuppressWarnings("unchecked")
    private static <T> T findComponent(BizId bizId, Class<T> targetClass) {
        ExtensionCoordinate bizCoordinate = ExtensionCoordinate.of(bizId.getBizCode(), targetClass.getName());
        IExtensionPoint extension = ExtensionManager.getInstance().findExtPoint(bizCoordinate);
        if (Objects.isNull(extension)) {
            ExtensionCoordinate systemCoordinate = ExtensionCoordinate.of(IExtensionPoint.SYSTEM_BIZ_CODE,
                    targetClass.getName());
            extension = ExtensionManager.getInstance().findExtPoint(systemCoordinate);
        }

        if (Objects.isNull(extension)) {
            throw new MidFrameworkException("Not found extension: " + bizCoordinate);
        }
        return (T) extension;
    }

}
