package com.cgx.middleware.extension.demo2;

import com.cgx.middleware.extension.annotation.Extension;

@Extension(bizCode = "com.taobao", name = "下单检查", description = "淘宝下单检查扩展点实例")
public class TaoBaoOrderCheckExt implements OrderCheckExtPoint {

    @Override
    public void buyPreCheck(int price) {
        if (price > 1000000 || price < 0) {
            throw new IllegalArgumentException("Invalid price of " + price);
        }
    }

}
