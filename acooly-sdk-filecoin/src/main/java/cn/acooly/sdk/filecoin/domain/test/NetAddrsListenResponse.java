/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-18 16:56
 */
package cn.acooly.sdk.filecoin.domain.test;

import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhangpu
 * @date 2021-06-18 16:56
 */
@Slf4j
public class NetAddrsListenResponse extends JsonRpcResponse<NetAddrsListenResponse.NetAddrsListenResult> {


    /**
     * 定义成功相关的result对应
     */
    @Data
    public static class NetAddrsListenResult {
        @JSONField(name = "ID")
        private String id;
        @JSONField(name = "Addrs")
        private List<String> addrs;
    }

}
