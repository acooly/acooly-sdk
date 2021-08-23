/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-05 23:53
 */
package cn.acooly.sdk.coinapi.fil;

/**
 * FIL全网数据服务
 *
 * @author zhangpu
 * @date 2021-06-05 23:53
 * @see cn.acooly.sdk.coinapi.explorer.CoinExplorer
 */
@Deprecated
public interface FileCoinNetworkService {

    /**
     * 全网汇总数据
     *
     * @return
     */
    FileCoinNetworkInfo overview();

}
