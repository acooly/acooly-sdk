/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-27 16:02
 */
package cn.acooly.sdk.filecoin.utils;

import com.acooly.core.utils.BigMoney;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author zhangpu
 * @date 2021-08-27 16:02
 */
@Slf4j
public class FilecoinUtils {

    public static BigMoney ATTO_BIG_MONEY = BigMoney.valueOf(BigDecimal.valueOf(Math.pow(10, 18)));

    /**
     * 转换消息中的货币金额为正常可读
     * attoFIL -> FIL
     *
     * @param messageAmount 单位attoFIL(10^-18)
     * @return
     */
    public static BigMoney toBigMoney(String messageAmount) {
        BigMoney bigMoney = BigMoney.valueOf(messageAmount);
        bigMoney.divisionBy(ATTO_BIG_MONEY);
        return bigMoney;
    }

}
