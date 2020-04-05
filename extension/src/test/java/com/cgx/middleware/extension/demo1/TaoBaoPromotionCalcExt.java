package com.cgx.middleware.extension.demo1;

import com.cgx.middleware.extension.annotation.Extension;

@Extension(bizCode = "com.taobao", name = "淘宝优惠计算", description = "淘宝优惠计算扩展点实例")
public class TaoBaoPromotionCalcExt implements PromotionCalcExtPoint {

    @Override
    public int calcPromotion() {
        return 80;
    }

}
