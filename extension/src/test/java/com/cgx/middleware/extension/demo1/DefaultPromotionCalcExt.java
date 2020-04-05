package com.cgx.middleware.extension.demo1;

import com.cgx.middleware.extension.annotation.SystemImpl;

@SystemImpl(name = "系统实现", description = "这是系统实现")
public class DefaultPromotionCalcExt implements PromotionCalcExtPoint {

    @Override
    public int calcPromotion() {
        return 100;
    }

}
