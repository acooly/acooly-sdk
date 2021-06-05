<!-- title: 加密货币数据组件 -->
<!-- name: acooly-sdk-coinapi -->
<!-- type: resources -->
<!-- author: zhangpu -->
<!-- date: 2021-06-04 -->

加密货币数据组件
====
acooly-sdk-coinapi
----

## 简介

通过第三方借口或数据，提供实时常用的加密数字货币相关的数据接口能力。目前包括：

* 行情数据（ticker）：提供币币汇率行情的实时查询。

## 集成

```xml

<dependency>
    <groupId>cn.acooly</groupId>
    <artifactId>acooly-sdk-coinapi</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

> 目前最新版本：`acooly.version`为`5.0.0-SNAPSHOT`

## 使用

## 参数及配置

>注意：所有的参数只有提供方的apiKey(accessKey)是必须配置的，请在启动组件前，在对应的提供方注册获得。

```ini
# [可选] 组件开关
acooly.sdk.coinapi.enable=true
# [可选] 缓存配置（单位秒）
acooly.sdk.coinapi.cache.enable=true
acooly.sdk.coinapi.cache.timeout=120
# 蜜蜂查商业数据提供方：https://data.mifengcha.com/
## [可选] 网关地址
acooly.sdk.coinapi.mifengcha.url=https://data.mifengcha.com/api
## [必选] apiKey参数，请在提供方注册后，获得apiKey
acooly.sdk.coinapi.mifengcha.accessKey=asdfasdfasdf
## [可选] 超时时间配置（秒）
acooly.sdk.coinapi.mifengcha.connTimeout=10
acooly.sdk.coinapi.mifengcha.readTimeout=5
# 天行数据提供方：https://www.tianapi.com/
## [可选] 网关地址
acooly.sdk.coinapi.tianapi.url=http://api.tianapi.com
## [必选] apiKey参数
acooly.sdk.coinapi.tianapi.accessKey=asdfasdfasdf
## [可选] 超时时间配置（秒）
acooly.sdk.coinapi.tianapi.connTimeout=10
acooly.sdk.coinapi.tianapi.readTimeout=5
```

### 接口及代码

#### 币币行情（汇率）

本组件提供汇率的实时查询，请在Spring容器注入`CoinApiService`接口直接使用。组件内集成多个提供方的数据来源实现，并统一依次获得成功为止则返回汇率数据。

```java
/**
 * 币币汇率查询
 *
 * @author zhangpu
 * @date 2021-06-03 14:34
 */
public interface CoinApiService extends Named, Ordered {

    /**
     * 币币兑换行情查询
     *
     * @param symbol
     * @return
     */
    Ticker ticker(String symbol);

    /**
     * 币币兑换行情查询
     *
     * @param from
     * @param to
     * @return
     */
    default Ticker ticker(DigitCurrency from, DigitCurrency to) {
        String symbol = from.code() + to.code();
        return ticker(symbol);
    }
}
```

#### Fil全网汇总数据

提供Fil挖矿的全网基础数据查询，数据主要包括：

* 区块高度 : 820,212
* 最新区块时间 : 1分6秒前
* 全网有效算力 : 6.042 EiB
* 活跃矿工数 : 2414
* 每区块奖励 : 25.4389 FIL
* 24h平均挖矿收益 : 0.0566 FIL/TiB
* 近24h产出量 : 356,964 FIL
* 当前扇区质押量 : 0.2259 FIL/32GiB
* FIL质押量 : 79,768,625 FIL
* 24h消息数 : 1,573,665
* FIL流通量 : 130,974,673 FIL
* 平均区块间隔 : 30.16 秒
* 平均每高度区块数量 : 4.85
* 新增算力成本 : 7.76 FIL/TiB
* 当前基础费率 : 0.47 nanoFIL
* FIL销毁量 : 26,087,446 FIL
* FIL总供给量 : 2,000,000,000 FIL
* FIL流通率 : 6.55%
* 最新价格 : $ 88.51

接口：

```java
/**
 * FIL全网数据服务
 *
 * @author zhangpu
 * @date 2021-06-05 23:53
 */
public interface FileCoinNetworkService {

    /**
     * 全网汇总数据
     *
     * @return
     */
    FileCoinNetworkInfo overview();

}
```

>Spring容器内注入该接口即可使用。



## 扩展

如果集成项目希望自己扩展数据提供方，可以在目标集成工程内实现`CoinApiService`接口，并通过`@Component`注入到Spring容器中，及可用。

## changelog

### 5.0.0-SNAPSHOT.20210606

2021-06-06

* 提供基于数据抓取的FIL全网基础数据查询

### 5.0.0-SNAPSHOT.20210604

2021-06-04

* 完成加密货币行情查询(ticker)组件的单个汇率查询开发(例如：filustd表示fil与usdt的汇率行情)，并提供按币种的2分钟本地缓存。
* 完成两个数据提供方的实现：蜜蜂查商业数据和天行数据（多个提供方时，根据实现的ordered顺序执行，直到找到可用的提供方，则返回）可通过注册免费账号的apikey配置进行测试。
* 提供可配置时长的本地缓存能力, `acooly.sdk.coinapi.cache.enable=true` 和 `acooly.sdk.coinapi.cache.timeout=120`（单位秒）

