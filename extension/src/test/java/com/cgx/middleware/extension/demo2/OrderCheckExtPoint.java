package com.cgx.middleware.extension.demo2;

import com.cgx.middleware.extension.model.IExtensionPoint;

public interface OrderCheckExtPoint extends IExtensionPoint {

    default void buyPreCheck(int price) {
        throw IExtensionPoint.NO_ROUTER_EXCEPTION;
    }

}