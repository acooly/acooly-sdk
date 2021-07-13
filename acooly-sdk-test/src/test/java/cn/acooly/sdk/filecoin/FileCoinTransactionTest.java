/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-12 13:34
 */
package cn.acooly.sdk.filecoin;

import cn.acooly.sdk.filecoin.rpclient.FileCoinMpool;
import cn.acooly.sdk.filecoin.rpclient.FileCoinWallet;
import cn.acooly.sdk.filecoin.service.FileCoinTransaction;
import cn.acooly.sdk.filecoin.service.FileCoinTransactionImpl;
import cn.acooly.sdk.filecoin.service.support.WithdrawOrder;
import cn.acooly.sdk.filecoin.service.support.WithdrawResult;
import com.acooly.core.utils.BigMoney;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-07-12 13:34
 */
@Slf4j
public class FileCoinTransactionTest extends FileCoinRpcBaseTest {

    FileCoinMpool fileCoinMpool = new FileCoinMpool(transport);
    FileCoinWallet fileCoinWallet = new FileCoinWallet(transport);

    FileCoinTransaction fileCoinTransaction = new FileCoinTransactionImpl(fileCoinWallet, fileCoinMpool);


    String from = "t1vwj5ia2ge2h2fydqage3k2csibirdpz2k7bbkfi";
    String to = "t1sgzkhqhzdflteflpjkpjn4nzqmr5boq23calvwy";


    @Test
    public void testWithdraw() {
        WithdrawOrder order = WithdrawOrder.builder().from(from).to(to).params("withdraw")
                .amount(BigMoney.valueOf("1")).build();
        WithdrawResult result = fileCoinTransaction.withdraw(order);
        log.info("withdraw result: {} ", result);
    }
}
