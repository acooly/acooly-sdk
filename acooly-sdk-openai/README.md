<!-- title: OpenAI-SDK -->
<!-- name: acooly-sdk-openai -->
<!-- type: resources -->
<!-- author: zhangpu -->
<!-- date: 2023-05-17 -->

# 简介

基于OpenAI官方接口封装的Java-SDK，提供常用的接口封装和代码调用文档说明。

* OpenAI文档地址：https://platform.openai.com/docs/introduction
* OpenAIAPI文档：https://platform.openai.com/docs/api-reference

>注意：以上文档需要科学上网

# 集成

```xml

<dependency>
    <groupId>cn.acooly</groupId>
    <artifactId>acooly-sdk-openai</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

> 目前最新版本：`acooly.version`为`5.2.0-SNAPSHOT`，框架内使用，最新版本可以由框架内的parent自动管理

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

本组件完整配置：

```ini
# [必选] OpenAI平台的apiKey，从OpenAI官方账号处获得，开发环境时，请防止到本地配置文件，不要上次到代码服务器
acooly.sdk.openai.gateway.key=sk-6W3Xe..........
# [可选] OpenAI平台的网关地址，默认为官方地址。
acooly.sdk.openai.gateway.url=https://api.openai.com
# [可选] 网络连接超时时间配置（秒）
acooly.sdk.openai.gateway.conn-timeout=30
# [可选] 网络读取超时时间配置（秒）
acooly.sdk.openai.gateway.read-timeout=10
# [可选] 是否开启网络代理服务及相关配置，国内开发时，可能需要科学上网访问国外的网关（默认关闭：false）
acooly.sdk.openai.proxy.enable=false
acooly.sdk.openai.proxy.host=127.0.0.1
acooly.sdk.openai.proxy.port=19180
```

## 最简配置

```ini
# [必选] OpenAI平台的apiKey，从OpenAI官方账号处获得，开发环境时，请防止到本地配置文件，不要上次到代码服务器
acooly.sdk.openai.gateway.key=sk-6W3Xe..........
```


# 使用

1. 集成和配置完成后，直接在目标工程和模块注入`OpenAiSdkService`服务，调用对应的接口方法，请求报文中有javadoc对参数的说明。

可参考：

1. 单元测试：acooly-sdk-test模块下：`cn.acooly.sdk.openai.OpenAiSdkServiceTest`

# changelog

# 5.2.0-SNAPSHOT.202300517

* 首次发布SDK，支持list-models, retrieve-model chat-completions, edits等接口。
