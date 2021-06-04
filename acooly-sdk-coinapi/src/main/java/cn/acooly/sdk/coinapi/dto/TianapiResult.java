/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-03 14:43
 */
package cn.acooly.sdk.coinapi.dto;

import com.acooly.core.common.facade.DtoBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhangpu
 * @date 2021-06-03 14:43
 */
@Getter
@Setter
public class TianapiResult extends DtoBase {

    private String code;
    private String msg;
    private List<Ticker> newslist;
}
