/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2024-05-03 17:31
 */
package cn.acooly.sdk.coinapi.antpool;

import cn.acooly.sdk.coinapi.enums.CoinApiErrors;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Strings;
import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * Ant Pool的API接口封装工具
 * <p>
 * <li>简介：按需封装需要的接口，首个接口为AntPool的计算器
 * <li>缓存：相同参数请求，默认5秒内缓存，降低网络请求压力
 * <p>
 * doc: https://www.antpool.com/userApiGuide
 * @date 2024-05-03 17:31
 */
@Slf4j
public class AntPoolApis {

    String gateway = "https://antpool.com/api";
    private String accessKey;
    private String secretKey;
    private String puid;
    /**
     * 读取超时时间(秒)
     */
    private long readTimeout = 10;
    /**
     * 链接超时时间(秒)
     */
    private long connectTimeout = 10;


    /**
     * 代理服务器host
     * 与proxyPort一起使用，不为空则启用代理
     */
    private String proxyHost;

    /**
     * 代理服务器端口
     * 与proxyHost一起使用，不为空则启用代理
     */
    private Integer proxyPort;

    /**
     * 缓存超时时间(秒)
     */
    private Integer cacheExpires = 5;

    /**
     * 缓存最大数量
     */
    private Integer cacheMaxSize = 100;

    /**
     * 流控器,如果需要则注入
     * 专用：`acooly-component-cache`组件的`RateChecker
     */
//    private RateChecker rateChecker;


    private volatile Cache<String, Object> cache = null;

    private static final String ANT_POOL_API_CACHE = "ANT_POOL_API_CACHE";


    private AntPoolApis(String puid, String accessKey, String secretKey) {
        this.puid = puid;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public static AntPoolApis of(String puid, String accessKey, String secretKey) {
        return new AntPoolApis(puid, accessKey, secretKey);
    }

    public AntPoolApis proxy(String proxyHost, Integer proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        return this;
    }

    public AntPoolApis readTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public AntPoolApis connectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public AntPoolApis cache(Integer cacheExpires, Integer cacheMaxSize) {
        this.cacheExpires = cacheExpires;
        this.cacheMaxSize = cacheMaxSize;
        return this;
    }

//    public AntPoolApis rateChecker(RateChecker rateChecker) {
//        this.rateChecker = rateChecker;
//        return this;
//    }


    /**
     * 收益计算器
     *
     * @param hashRateT   T级算力
     * @param coinType    币种：BTC,ETH,LTC,BCH等
     * @param feePercent  手续费百分比（可选），默认：0；0.04表示4%
     * @param networkDiff 网络难度（可选）
     */
    public CoinCalcResult coinCalculator(@NotNull Integer hashRateT, @NotNull String coinType,
                                         String feePercent, String networkDiff) {

        // 缓存处理
        String cacheKey = getCoinCalculatorCacheKey(hashRateT, coinType, feePercent, networkDiff);
        CoinCalcResult coinCalcResult = (CoinCalcResult) getCache().getIfPresent(cacheKey);
        if (coinCalcResult != null) {
            log.info("Coin-calc Use Cache: {}", cacheKey);
            return coinCalcResult;
        }

        // 限流处理（本来应该是全局服务接口现在，但目前只有这一个接口，先直接写这里简单用起来）
//        if (rateChecker != null) {
//            // 10分钟内部超过600次(1次/秒)，直接抛出异常
//            if (!rateChecker.check("ANT_POOL_API_RATE_CHECKER", 1000, 1L)) {
//                throw new BusinessException(CoinApiErrors.RATE_LIMIT_ERROR, "AntPool限流：10分钟内不超过600次，请稍后再试试。");
//            }
//        }

        String nonce = String.valueOf(System.currentTimeMillis());
        String signature = signature(this.puid, this.accessKey, nonce, this.secretKey);
        long tHashRate = 1000000000000L;
        FormBody.Builder builder = new FormBody.Builder()
                .add("key", this.accessKey)
                .add("nonce", nonce)
                .add("signature", signature)
                .add("coinType", coinType)
                .add("hashInput", String.valueOf(hashRateT * tHashRate));
        if (Strings.isNotBlank(feePercent)) {
            builder.add("feePercent", feePercent);
        } else {
            builder.add("feePercent", "0");
        }
        if (Strings.isNotBlank(networkDiff)) {
            builder.add("networkDiff", networkDiff);
        }

        FormBody formBody = builder.build();
        OkHttpClient client = getOkHttpClient();
        String url = this.gateway + "/coinCalculator.htm";
        Request request = new Request.Builder().url(url).post(formBody).build();
        log.info("request url: {}, body: {}", url, formBody);
        try (Response response = client.newCall(request).execute()) {
            String body = response.body().string();
            log.info("response: {}", body);
            CoinCalcResponse coinCalcResponse = JSON.parseObject(body, CoinCalcResponse.class);
            if (coinCalcResponse.getCode() != 0) {
                throw new BusinessException(coinCalcResponse.getMessage(), "ANT_POOL_API_ERROR");
            }
            CoinCalcResult result = coinCalcResponse.getData();
            getCache().put(cacheKey, result);
            return result;
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(CoinApiErrors.NETWORK_ERROR, e.getMessage());
        }
    }

    public CoinCalcResult coinCalculator(@NotNull Integer hashRateT, @NotNull String coinType,
                                         String feePercent) {
        return coinCalculator(hashRateT, coinType, feePercent, null);
    }

    public CoinCalcResult coinCalculator(@NotNull Integer hashRateT, @NotNull String coinType) {
        return coinCalculator(hashRateT, coinType, null, null);
    }

    protected OkHttpClient getOkHttpClient() {
        // 定义代理服务器的地址和端口
        if (Strings.isNotBlank(this.proxyHost) && this.proxyPort != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.proxyHost, this.proxyPort));
            OkHttpClient client = new OkHttpClient.Builder()
                    .proxy(proxy)
                    .readTimeout(this.readTimeout, java.util.concurrent.TimeUnit.SECONDS)
                    .connectTimeout(this.connectTimeout, java.util.concurrent.TimeUnit.SECONDS)
                    .build();
            return client;
        } else {
            return new OkHttpClient.Builder().build();
        }
    }


    protected String signature(String userId, String key, String nonce, String secret) {
        try {
            String data = userId + key + nonce;
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            hmacSha256.init(secretKey);
            String hmac = Hex.encodeHexString(hmacSha256.doFinal(data.getBytes())).toUpperCase();
            return hmac;
        } catch (Exception e) {
            log.error("signature error", e);
            throw new BusinessException(CommonErrorCodes.UNAUTHENTICATED_ERROR, "hmac signature error");
        }

    }

    protected static boolean checkSignature(String userId, String key, String nonce, String secret, String signature) throws Exception {
        String data = userId + key + nonce;
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        hmacSha256.init(secretKey);
        String signatrueTmp = Hex.encodeHexString(hmacSha256.doFinal(data.getBytes())).toUpperCase();
        if (signature.equals(signatrueTmp)) {
            return true;
        }
        return false;
    }

    protected String getCoinCalculatorCacheKey(@NotNull Integer hashRateT, @NotNull String coinType,
                                               String feePercent, String networkDiff) {
        return ANT_POOL_API_CACHE + ":" + hashRateT + "_" + coinType + Strings.trimToEmpty(feePercent) + Strings.trimToEmpty(networkDiff);
    }

    protected Cache<String, Object> getCache() {
        if (this.cache == null) {
            this.cache = Caffeine.newBuilder().expireAfterWrite(this.cacheExpires, TimeUnit.SECONDS)
                    .maximumSize(this.cacheMaxSize).build();
        }
        return this.cache;
    }

}
