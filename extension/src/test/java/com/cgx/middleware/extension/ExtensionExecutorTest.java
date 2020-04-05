package com.cgx.middleware.extension;

import com.cgx.middleware.extension.demo1.PromotionCalcExtPoint;
import com.cgx.middleware.extension.demo2.OrderCheckExtPoint;
import com.cgx.middleware.extension.exception.MidFrameworkException;
import com.cgx.middleware.extension.model.BizId;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class ExtensionExecutorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testTaoBaoExt() {
        BizId bizId = new BizId("com.taobao");
        int promotion = ExtensionExecutor.execute(bizId, PromotionCalcExtPoint.class,
                PromotionCalcExtPoint::calcPromotion);
        Assert.assertEquals(promotion, 80);
    }

    @Test
    public void testTmallExt() {
        BizId bizId = new BizId("com.tmall");
        int promotion = ExtensionExecutor.execute(bizId, PromotionCalcExtPoint.class,
                PromotionCalcExtPoint::calcPromotion);
        Assert.assertEquals(promotion, 90);
    }

    @Test
    public void testSystemImpl() {
        BizId unknown = new BizId("com.unknown");
        int promotion = ExtensionExecutor.execute(unknown, PromotionCalcExtPoint.class,
                PromotionCalcExtPoint::calcPromotion);
        Assert.assertEquals(promotion, 100);
    }

    @Test
    public void testConsumer() {
        int price = 666;
        ExtensionExecutor.consume(new BizId("com.taobao"), OrderCheckExtPoint.class,
                orderCheck -> orderCheck.buyPreCheck(price));
    }

    @Test(expected = MidFrameworkException.class)
    public void testEmpty() throws MidFrameworkException {
        ExtensionExecutor.execute(new BizId("com.not.exists"), PersonExtPoint.class, PersonExtPoint::sleep);
        thrown.expect(MidFrameworkException.class);
    }

}