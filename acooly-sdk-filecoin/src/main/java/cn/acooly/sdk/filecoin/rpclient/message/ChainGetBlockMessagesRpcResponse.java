/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-29 13:45
 */
package cn.acooly.sdk.filecoin.rpclient.message;

import cn.acooly.sdk.filecoin.domain.FilMessage;
import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhangpu
 * @date 2021-06-29 13:45
 */
@Slf4j
public class ChainGetBlockMessagesRpcResponse extends JsonRpcResponse<ChainGetBlockMessagesRpcResponse.ChainGetBlockMessages> {


    @Data
    @ToString
    public static class ChainGetBlockMessages {

        @JSONField(name = "BlsMessages")
        private List<FilMessage> blsMessages;

        @JSONField(name = "SecpkMessages")
        private List<FilMessage> secpkMessages;

    }
}
