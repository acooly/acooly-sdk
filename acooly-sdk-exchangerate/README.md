<!-- title: 法定货币汇率组件 -->
<!-- name: acooly-sdk-exchangerate -->
法定货币汇率组件
====
acooly-sdk-exchangerate
----

## 简介

通过第三方借口或数据，提供实时常用的法定货币间的汇率及计算。

## 集成

```xml
<dependency>
    <groupId>cn.acooly</groupId>
    <artifactId>acooly-sdk-exchangerate</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

>目前最新版本：`acooly.version`为`5.0.0-SNAPSHOT`

## 使用

本组件提供汇率的实时查询，请在Spring容器注入`ExchangeRateService`接口直接使用。组件内集成多个提供方的数据来源实现，并统一依次获得成功为止则返回汇率数据。

```java
/**
 * 常用法定货币汇率查询
 *
 * @author zhangpu
 * @date 2021-05-27 23:33
 */
public interface ExchangeRateService extends Named {

    /**
     * 常用货币汇率列表
     *
     * @param currency
     * @return
     */
    ExchangeRates rates(LegalCurrency currency);

    /**
     * 获取单个汇率
     * 以from的1个标准单位进行汇率计算，返回最大四位小数值（四舍五入）
     *
     * @param from
     * @param to
     * @return
     */
    BigDecimal rate(LegalCurrency from, LegalCurrency to);
}
```

## changelog

### 5.0.0-SNAPSHOT

2021-05-31

* 完成法定货币汇率查询组件的单个汇率查询开发，并提供按币种的2分钟本地缓存。
* 完成法定货币汇率查询组件的组件架构设计，支持多种数据提供方实现可增量插入，目前支持：exchange-rates.org的数据提供实现。

