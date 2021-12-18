/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 23:05
 */
package cn.acooly.sdk.swft;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2021-09-16 23:05
 */
@Data
public class SwftProperties {

    public static final String PREFIX = "acooly.sdk.swft";

    public static final String SUCCESS_CODE = "800";

    private boolean enable = true;

    /**
     * 用于标识是哪个平台创建的订单，需要与SWFT BlockChain进行协商设置
     */
    private String sourceFlag = "wecoinbank";

    private Gateway gateway = new Gateway();

    @Data
    @NoArgsConstructor
    public class Gateway {

        /**
         * 网关地址
         */
        private String url = "https://www.swftc.info";

        /**
         * auth token
         */
        private String token;

        /**
         * 连接超时时长(秒)
         */
        private int connTimeout = 30;
        /**
         * 读取超时时长(秒)
         */
        private int readTimeout = 10;

        public Gateway(String url, String token) {
            this.url = url;
            this.token = token;
        }

    }
}
