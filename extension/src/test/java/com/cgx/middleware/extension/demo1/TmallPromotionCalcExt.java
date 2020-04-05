package com.cgx.middleware.extension.demo1;

import com.cgx.middleware.extension.annotation.Extension;

@Extension(bizCode = "com.tmall", name = "天猫优惠计算", description = "天猫优惠计算扩展点实例")
public class TmallPromotionCalcExt implements PromotionCalcExtPoint {

    @Override
    public int calcPromotion() {
        return 90;
    }

}
