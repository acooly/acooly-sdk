/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 16:04
 */
package cn.acooly.sdk.filecoin.rpclient.message;

import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhangpu
 * @date 2021-06-23 16:04
 */
@Slf4j
public class ChainGetTipSetByHeightRpcResponse extends JsonRpcResponse<ChainGetTipSetByHeightRpcResponse.ChainGetTipSetByHeight> {


    @Data
    @ToString
    public static class ChainGetTipSetByHeight {

        private List<String> filCids = Lists.newArrayList();

        @JSONField(name = "Height")
        private Integer height;


    }

}
