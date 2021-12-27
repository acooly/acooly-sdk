<!-- title: 加密货币数据组件 -->
<!-- name: acooly-sdk-coinapi -->
<!-- type: resources -->
<!-- author: zhangpu -->
<!-- date: 2021-06-04 -->

加密货币数据组件
====
acooly-sdk-coinapi
----

# 简介

通过第三方借口或数据，提供实时常用的加密数字货币相关的数据接口能力。目前包括：

* 行情数据（ticker）：提供币币汇率行情的实时查询。
* 数字火币浏览器：目前支持BTC,ETH和FIL

# 集成

## 组件集成

```xml

<dependency>
    <groupId>cn.acooly</groupId>
    <artifactId>acooly-sdk-coinapi</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

> 目前最新版本：`acooly.version`为`5.0.0-SNAPSHOT`

## 网络配置

我们把组件服务需要的网络环境分为：全网（国内可访问）和外网（国外可访问）

特别注意：部分SDK访问的是国外提供商，需要合理上网。如果使用代理，请设置对应参数，参数设置可以是Java系统参数，也可以是启动参数或环境变量。

### Java系统属性

```java
// 代理参数设置
System.setProperty("https.proxySet","true");
System.setProperty("https.proxyHost","xxx");
System.setProperty("https.proxyPort","xxxx");
```

# 特性

## 浏览器数据

建议采用`外网`访问

采用从多个平台或交易所通过API或爬虫方式获取数据，每种币有多个实现，多个实现中如果有一个成功返回数据则OK，同时支持缓存（本地）配置。

目前支持的币种：BTC,ETH和FIL，的提供方主要有：

* BTC和ETC： blockchair.com ,btc.com
* FIL: filfox.io

```ini
# ------- 数字货币浏览器配置 ------------
## [可选] 浏览器缓存开关
acooly.sdk.coinapi.explorer.cache.enable=true
## [可选] 浏览器缓存时间（秒）
acooly.sdk.coinapi.explorer.cache.timeout=120
## [可选] 浏览器缓存数量（支持多少种币）
acooly.sdk.coinapi.explorer.cache.size=20
```

服务：`cn.acooly.sdk.coinapi.explorer.CoinExplorerService`

```java
public class CoinExplorerService {
    BitcoinOverview btc();

    EthereumOverview eth();

    FilecoinOverview fil();
}
```

## 价格及报价数据

必须采用`外网`访问

提供数字货币的实时价格及相关报价查询，目前按提供商集成了两个SDK接口。

* 币安: 交易所简单价格兑换查询，提供数字货币间兑换价格（一般兑换USDT来实现与法币的汇率）`BinanceQuoteService`
* coinmarketcap: 采用免费ApiKey（333次/天）方式查询数字货币实时(1分钟更新)报价数据。

这里详细说明`coinmarketcap`SDK的使用。

1. 定时拉取`coinmarketcap`的最新价格行情数据（`../quotes/latest`）,因为是免费接口，安全考虑，最快请控制到30分钟一次。
2. 特别需要注意的是，组件提供的定时任务服务`cn.acooly.sdk.coinapi.platform.coinmarketcap.CoinmarketcapQuoteScheduler`默认通过配置文件控制频率，只支持单机，不支持分布式，如果是双节点，请控制多节点拉取的并发问题。强烈建议通过`acooly-component-schedule`分布式调度组件来配置定时任务。
3. 数据拉取后，统一存储到数据库中做缓存，实体：`CoinmarketcapQuotes`。
4. 通过`cn.acooly.sdk.coinapi.platform.coinmarketcap.CoinmarketcapQuoteService`接口查询实时或历史的数字货币报价信息。

配置参数：

```ini
## Coinmarkcap平台参数（数字货币报价数据）
# [必选] Coinmarkcap平台的apiKey。
acooly.sdk.coinapi.coinmarketcap.secretKey=1111111-d120-41f9-8812-2222222222
# [可选] 链接超时时长（秒，默认：10）
acooly.sdk.coinapi.coinmarketcap.connTimeout=10
# [可选] 读取超时时长（秒，默认：5）
acooly.sdk.coinapi.coinmarketcap.readTimeout=5
# [可选] 是否开启本地定时任务，不支持分布式，如果需要分布式请使用组件（默认关闭：false）
acooly.sdk.coinapi.coinmarketcap.scheduleEnable=true
# [可选] 默认间隔30分钟（免费APIKEY一天333次）
acooly.sdk.coinapi.coinmarketcap.scheduleInterval=1800000
```



## 行情数据

> 注意：所有的参数只有提供方的apiKey(accessKey)是必须配置的，请在启动组件前，在对应的提供方注册获得。

```ini
## -------- 行情数据（ticker）配置 ------------
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

>如果集成项目希望自己扩展数据提供方，可以在目标集成工程内实现`CoinApiService`接口，并通过`@Component`注入到Spring容器中，及可用。

# changelog

## 5.0.0-SNAPSHOT.20211227

* 2021-12-27 - 数字货币报价SDK集成coinmarketcap平台API，并采用数据库缓存方式提供本地查询服务 - [zhangpu] 4f57d3d
* 2021-12-25 - 完成数字货币浏览器扩展：增加每种货币的当前价格 - [zhangpu] 86991b3
* 2021-12-25 - 数字货币浏览器增加BTC和ETH的Blockchair的API提供集成，并设置为最高优先级。- [zhangpu] 584f172

## 5.0.0-SNAPSHOT.20210824

* 2021-08-18 - 1、exchangerate网站已改版，更新获取数据的逻辑 2、完善rates方法 - [lilin] 545c1bc
* 2021-08-23 - 完成数字货币浏览器的全网数据查询SDK的结构设计，统一接口，运行每个币种多个实现，只要其中一个实现正确获取数据则返回，同时支持针对浏览器查询数据的统一缓存配置（默认本地2分钟） - [zhangpu] 1bd6413
* 2021-08-23 - 基于新的数字货币浏览器查询框架，开发了BTC和ETH的全网数据查询SDK封装，迁移原fil独立全网数据查询到explorer模块 - [zhangpu] 0a7a2a3
* 2021-08-23 - 完成CoinExplorerService整合服务的开发，各币种可方便的直接调用，请在目标工程直接注入`CoinExplorerService`服务使用即可。 - [zhangpu] ae400e8
* 2021-08-24 - 为同币种多个实现增加排序功能，通过@Ordered标签或接口 - [zhangpu] 1491577

## 5.0.0-SNAPSHOT.20210606

2021-06-06

* 提供基于数据抓取的FIL全网基础数据查询

## 5.0.0-SNAPSHOT.20210604

2021-06-04

* 完成加密货币行情查询(ticker)组件的单个汇率查询开发(例如：filustd表示fil与usdt的汇率行情)，并提供按币种的2分钟本地缓存。
* 完成两个数据提供方的实现：蜜蜂查商业数据和天行数据（多个提供方时，根据实现的ordered顺序执行，直到找到可用的提供方，则返回）可通过注册免费账号的apikey配置进行测试。
* 提供可配置时长的本地缓存能力, `acooly.sdk.coinapi.cache.enable=true` 和 `acooly.sdk.coinapi.cache.timeout=120`（单位秒）

