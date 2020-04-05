package com.cgx.middleware.extension.demo1;

import com.cgx.middleware.extension.model.IExtensionPoint;

public interface PromotionCalcExtPoint extends IExtensionPoint {

    default int calcPromotion() {
        throw IExtensionPoint.NO_ROUTER_EXCEPTION;
    }

}
