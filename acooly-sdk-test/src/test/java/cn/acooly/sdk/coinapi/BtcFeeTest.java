/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2024-04-13 12:00
 */
package cn.acooly.sdk.coinapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2024-04-13 12:00
 */
@Slf4j
public class BtcFeeTest {

    @Test
    public void test1() {
        // 1单位难度：做2^32次hash运算
        long oneDiff = Double.valueOf(Math.pow(2,32)).longValue();
        log.info("oneDiff:{}", oneDiff);
        // 当前难度
        long curDiff = 83950000000000l;
        log.info("curDiff:{}", curDiff);


    }

}
