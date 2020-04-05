package com.cgx.middleware.extension;

import com.cgx.middleware.extension.demo1.PromotionCalcExtPoint;
import com.cgx.middleware.extension.model.ExtensionCoordinate;
import org.junit.Assert;
import org.junit.Test;

public class ExtensionManagerTest {

    @Test
    public void testRegisterExtPoint() throws Exception {
        ExtensionCoordinate coord = ExtensionCoordinate.of("com.new.biz", PromotionCalcExtPoint.class.getName());

        PromotionCalcExtPoint newBizCalcExtPoint = new PromotionCalcExtPoint() {
            @Override
            public int calcPromotion() {
                return 44;
            }
        };
        ExtensionManager.getInstance().registerExtPoint(coord, newBizCalcExtPoint);

        PromotionCalcExtPoint extPoint = (PromotionCalcExtPoint) ExtensionManager.getInstance().findExtPoint(coord);
        Assert.assertNotNull(extPoint);
        Assert.assertEquals(extPoint.calcPromotion(), 44);
    }

}