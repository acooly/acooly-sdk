/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:07
 */
package cn.acooly.sdk.swft.message.dto;

import cn.acooly.sdk.swft.message.SwftResponse;
import com.acooly.core.common.facade.DtoBase;
import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author zhangpu
 * @date 2021-09-17 22:07
 */
@Data
public class BaseInfo extends InfoBase {

    /**
     * 即时汇率
     * "精确到小数点后十位
     * 接收货币/存入货币的汇率"
     */
    @NotEmpty
    private String instantRate;

    /**
     * 最低存储额
     * 精确到小数点后六位
     */
    @NotEmpty
    private String depositMin;

    /**
     * 最高存储额
     * 精确到小数点后六位
     */
    @NotEmpty
    private String depositMax;
}
