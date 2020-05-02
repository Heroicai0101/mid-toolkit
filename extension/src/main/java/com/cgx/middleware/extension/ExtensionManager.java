package com.cgx.middleware.extension;

import com.cgx.middleware.extension.model.ExtensionCoordinate;
import com.cgx.middleware.extension.model.IExtensionPoint;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 扩展点管理类
 */
public final class ExtensionManager {

    private static ExtensionManager instance = new ExtensionManager();

    private static Map<ExtensionCoordinate, IExtensionPoint> extensionHub = new ConcurrentHashMap<>(64);

    public static ExtensionManager getInstance() {
        return instance;
    }

    /**
     * 注册扩展点实例
     *
     * @param coord     扩展点实例坐标
     * @param extension 扩展点实例对象
     * @return 之前注册的扩展点实例; 如果不为null, 表示发生了重复注册!
     */
    public IExtensionPoint registerExtPoint(ExtensionCoordinate coord, IExtensionPoint extension) {
        return extensionHub.put(coord, extension);
    }

    /**
     * 查找扩展点实例
     */
    public IExtensionPoint findExtPoint(ExtensionCoordinate coord) {
        return extensionHub.get(coord);
    }

    /**
     * 全部扩展点实例; 可用户扩展点可视化管理
     */
    public Map<ExtensionCoordinate, IExtensionPoint> listAllExtPoint() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(extensionHub));
    }

}
