<!-- title: 快递查询SDK -->
<!-- name: acooly-sdk-aliyun-express -->
<!-- type: resources -->
<!-- author: zhangpu -->
<!-- date: 2021-10-14 -->

# 简介

基于阿里云市场服务提供商易源数据的快递信息和轨迹查询接口的封装SDK

阿里云市场地址：https://market.aliyun.com/products/57126001/cmapi010996.html?spm=5176.78296.J_4638034320.1.1a5e5d76tbZFcG#sku=yuncode499600008

# 集成

```xml

<dependency>
    <groupId>cn.acooly</groupId>
    <artifactId>acooly-sdk-aliyun-express</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

> 目前最新版本：`acooly.version`为`5.0.0-SNAPSHOT`，框架内使用，最新版本可以由框架内的parent自动管理

# 配置

## 安全身份参数配置

所有的阿里云市场SDK组件都依赖`acooly-sdk-aliyun-common`模块内的阿里云身份参数配置，通一个阿里云用户，一般情况下，申请的所有阿里云市场API服务的身份参数都是一致的，所以可以通过该模块进行统一配置，不用不同组件反复配置。当然，如果有特殊情况，项目中需要为某个阿里云组件市场配置特定参数也可以在在组件参数中配置覆盖的。

```ini
## 阿里云市场用户身份配置
# [必填]（与下面appKey和appSecret组合二选一必填） ，本组件必填
# 阿里云的AppCode, 一般用于简单身份认证
acooly.sdk.aliyun.app-code=ac49b528xxxxxxxxxx5618947da

# [可选] appKey和appSecret组合与appCode二选一必填） ，本组件不填
# 签名认证的accessKey和secretKey
acooly.sdk.aliyun.app-key=xxxxx
acooly.sdk.aliyun.app-secret=yyyyy
```

> 认证：阿里云市场的API产品的安全认证方式是由产品确定的，具体使用那种认证，就必须配置以上对应的身份认证参数。比如该组件`acooly-sdk-aliyun-express`的提供方选择的是`appCode`方式简单认证，所以该组件`acooly.sdk.aliyun.app-code`参数为必填。

> 来源：以上上个参数来源与你购买了阿里云市场产品后，在你的阿里云`控制台`->`云市场`-`已购买的服务`模块中查看。

## SDK组件参数配置

本组件只需要配置的参数为：

```ini
acooly.sdk.aliyun.express.app-title=快递查询
# [可选] 快递查询网关地址。默认：https://ali-deliver.showapi.com
acooly.sdk.aliyun.express.gateway=https://ali-deliver.showapi.com
# [可选] 网络超时时间
acooly.sdk.aliyun.express.conn-timeout=10000
acooly.sdk.aliyun.express.read-timeout=10000
# [可选] 可覆盖`acooly.sdk.aliyun.xxx`的对应配置
acooly.sdk.aliyun.express.app-code=
acooly.sdk.aliyun.express.app-key=
acooly.sdk.aliyun.express.app-secret=
```

## 缓存配置

```ini
# [可选] 缓存开关（默认true，开启）
acooly.sdk.aliyun.express.cache.enable=true
# [可选] 缓存过期时长（单位秒，默认120秒）
acooly.sdk.aliyun.express.cache.timeout=120
```

## 最简配置

```ini
acooly.sdk.aliyun.app-code=acddxxxxxxxxxxxxx75618947ddd
```

# 使用

1. 集成和配置完成后，直接在目标工程和模块注入`ExpressService`服务，调用：`ExpressService.track`方法，根据快递单号查询快递信息和配送轨迹信息,但该轨迹没有状态信息。
2. 推荐使用服务`ExpressQueryService.query(...)`，提供统一的快递查询和详细轨迹查询。

可参考：

1. 单元测试：acooly-sdk-test模块下：`cn.acooly.sdk.aliyun.ExpressQueryServiceTest`
2. 控制器测试：acooly-sdk-test模块下：`cn.acooly.sdk.aliyun.express.ExpressQueryTestController`

# changelog

# 5.2.0-SNAPSHOT.20230410

* 新增支持详细轨迹查询的快递查询服务`ExpressQueryService.query(...)`

# 5.0.0-SNAPSHOT.20211014

2021-10-14

* 首次发布快递查询SDK
* 支持根据快递单号，快递公司等信息查询快递详情和轨迹信息

# 5.0.0-SNAPSHOT.20211021

* 2021-10-21 - 快递查询组件增加缓存功能（默认打开，120秒缓存），逻辑：根据单号+快递公司编码缓存快递信息到redis。定时过期。通过参数`acooly.sdk.aliyun.express.cache.enable=true`控制是否启用缓存，通过`acooly.sdk.aliyun.express.cache.timeout=120`控制缓存超时时间 - [zhangpu] bed67e1
