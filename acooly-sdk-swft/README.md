<!-- title: 闪兑SDK -->
<!-- name: acooly-sdk-swft -->
<!-- type: resources -->
<!-- author: zhangpu -->
<!-- date: 2021-12-10 -->

SWFT数据货币闪兑
====
acooly-sdk-coinapi
----

## 简介

通过闪兑提供主流数字货币的去中心化兑换。

## 集成

```xml

<dependency>
    <groupId>cn.acooly</groupId>
    <artifactId>acooly-sdk-swft</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

> 目前最新版本：`acooly.version`为`5.0.0-SNAPSHOT`

## SWFT文档

https://docs.google.com/spreadsheets/d/1LgjKhNGQRL1_TJmzrZfLjqW-FGWcJDI6uuzjuB7aBRM/edit#gid=821282425

## 核心业务流程

1. `queryCoinList` : 查询支持的币种（以及每个币种的编码，不支持兑换的币种列表）列表，检测采用定时拉取，本地缓存方式
2. `getBaseInfo` : 查看币种兑换的汇率（两种币的兑换汇率），限制参数（最大最小兑换额度）和手续费（SWFT平台费率和链上费用）
3. `exchangeCalc` : 根据`getBaseInfo`的数据，计算指定两种币，指定额度兑换后的数量
4. `accountExchange` : 闪兑订单接口，提交兑换订单请求。
    * 请求后，SWFT会返回一个对源币的平台存币地址
    * 兑换这想找个地址存入订单对应的源币
    * SWFT先根据SWFT手续费费率收取SWFT手续费（收源币）
    * SWFT会把剩下的源币按汇率兑换收币币种数量，然后充币到用户的收币地址（链上扣取费用）

5. `queryOrderState`：单笔订单状态查询接口
6. `queryAllTrade`：批量订单分页查询接口

## 案例

请参考单元测试`acooly-sdk/acooly-sdk-test`下：`cn.acooly.sdk.swft.SwftSdkServiceLocalTest`