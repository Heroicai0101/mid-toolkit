package com.cgx.middleware.extension.model;

import com.cgx.middleware.extension.exception.MidFrameworkException;

/**
 * 没有什么方法, 仅用作扩展点标记接口
 */
public interface IExtensionPoint {

    MidFrameworkException NO_ROUTER_EXCEPTION = new MidFrameworkException("No router");

    /** 扩展点接口命名规范: 扩展点接口名必须以ExtPt结尾 */
    String EXT_POINT_NAMING = "ExtPoint";

    /** 平台实现的扩展点实例注册统一用 $SYSTEM$ 这个bizCode */
    String SYSTEM_BIZ_CODE = "$SYSTEM$";

}
