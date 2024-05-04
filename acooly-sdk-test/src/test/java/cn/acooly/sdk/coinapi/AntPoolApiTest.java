/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2024-05-03 16:13
 */
package cn.acooly.sdk.coinapi;

import cn.acooly.sdk.coinapi.antpool.AntPoolApis;
import cn.acooly.sdk.coinapi.antpool.CoinCalcResult;
import com.acooly.core.utils.Tasks;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2024-05-03 16:13
 */
@Slf4j
public class AntPoolApiTest extends NetworkProxyTest {

    /**
     * AntPool的子账号名称（userId）,APIKEY和SECRET
     * 1、请先在AntPool官网注册账号：https://www.antpool.com/
     * 2、创建子账号
     * 3、在这里`https://www.antpool.com/userApi`点击`生成API`获取APIKEY和SECRET（主要要先记录，然后点击后面的`激活`）
     */
    String userId = "****";
    String APIKEY = "377dc5edfa*************14d3fca0";
    String SECRET = "f64da258c7*************4740cc895";


    @Test
    public void testCoinCalculatorWithAntPoolApis() {
        AntPoolApis antPoolApis = AntPoolApis.of(userId, APIKEY, SECRET)
//                .proxy(PROXY_HOST, PROXY_PORT)
                .readTimeout(10).connectTimeout(10)
                .cache(5, 1000);

        CoinCalcResult result = antPoolApis.coinCalculator(14, "BTC");
        result = antPoolApis.coinCalculator(14, "BTC");
        result = antPoolApis.coinCalculator(14, "BTC");
        log.info("result: {}", JSON.toJSONString(result));
    }

    @Test
    public void testBatchCoinCalculatorWithAntPoolApis() throws Exception {

        AntPoolApis antPoolApis = AntPoolApis.of(userId, APIKEY, SECRET)
                .proxy(PROXY_HOST, PROXY_PORT)
                .readTimeout(10).connectTimeout(10)
                .cache(5, 1000);

        Tasks.concurrentTasks(5, () -> {
            try {
                int index = RandomUtils.nextInt(2, 100);
                CoinCalcResult result = antPoolApis.coinCalculator(index, "BTC");
                log.info("Index: {}, result: {}", index, result);
            } catch (Exception e) {
                log.error("Error", e);
            }
        });

    }

}
