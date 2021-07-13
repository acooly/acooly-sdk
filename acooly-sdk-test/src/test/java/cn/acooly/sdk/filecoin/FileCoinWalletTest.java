/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 15:30
 */
package cn.acooly.sdk.filecoin;

import cn.acooly.sdk.filecoin.enums.KeyType;
import cn.acooly.sdk.filecoin.rpclient.FileCoinWallet;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangpu
 * @date 2021-06-23 15:30
 */
@Slf4j
public class FileCoinWalletTest extends FileCoinRpcBaseTest {

    private static FileCoinWallet fileCoinWallet;

    @BeforeClass
    public static void init() {
        fileCoinWallet = new FileCoinWallet(transport);
        log.info("完成初始化: {}", filecoinSdkProperties.getGateway());
    }


    /**
     * 测试: 创建钱包
     * t1sgzkhqhzdflteflpjkpjn4nzqmr5boq23calvwy
     */
    @Test
    public void testWalletNew() {
        String walletAddr = fileCoinWallet.walletNew(KeyType.secp256k1);
        log.info("new wallet Addr: {}", walletAddr);
    }

    @Test
    public void testWalletList() {
        List<String> walletAddrs = fileCoinWallet.walletList();
        log.info("list wallet Addrs size: {}", walletAddrs.size());
        for (String addr : walletAddrs) {
            System.out.println(addr);
        }
    }

    @Test
    public void testWalletBalance(){
        String address = "t1sgzkhqhzdflteflpjkpjn4nzqmr5boq23calvwy";
        BigDecimal balance = fileCoinWallet.walletBalance(address);
        log.info("walletBalance {} : {}",address, balance);
    }

    @Test
    public void testWalletBalances() {
        List<String> walletAddrs = fileCoinWallet.walletList();
        log.info("list wallet Addrs size: {}", walletAddrs.size());
        for (String addr : walletAddrs) {
            System.out.println(addr + " " + fileCoinWallet.walletBalance(addr));
        }
    }


}
