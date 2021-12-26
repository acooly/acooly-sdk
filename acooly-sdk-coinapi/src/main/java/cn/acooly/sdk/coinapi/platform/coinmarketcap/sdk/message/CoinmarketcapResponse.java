/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 23:23
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.message;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;

/**
 * @author zhangpu
 * @date 2021-12-25 23:23
 */
@Data
public class CoinmarketcapResponse extends InfoBase {

    public static final int SUCCESS_CODE = 0;

    private CoinmarketcapStatus status;

    private String data;
    
    public boolean success() {
        return status != null && status.getErrorCode() == SUCCESS_CODE;
    }
}
