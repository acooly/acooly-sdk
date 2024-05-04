/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2024-05-03 21:25
 */
package cn.acooly.sdk.coinapi.antpool;

import com.acooly.core.common.facade.InfoBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpu
 * @date 2024-05-03 21:25
 */
@Getter
@Setter
public class CoinCalcResponse extends InfoBase {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private CoinCalcResult data;

}
