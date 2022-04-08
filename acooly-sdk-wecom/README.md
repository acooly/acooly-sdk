<!-- title: 企业微信SDK -->
<!-- name: acooly-sdk-wecom -->
<!-- type: resources -->
<!-- author: zhangpu -->
<!-- date: 2022-4-8 -->

# 简介

企业微信的API封装SDK
1. 机器人推送

# 集成

```xml

<dependency>
    <groupId>cn.acooly</groupId>
    <artifactId>acooly-sdk-wecom</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

> 目前最新版本：`acooly.version`为`5.0.0-SNAPSHOT`，框架内使用，最新版本可以由框架内的parent自动管理


# 使用

## 机器人推送

直接静态工具类调用，支持多种消息格式。

```java
TextWeComMsg weComMsg = new TextWeComMsg("这是推送的文本信息");
// 添加@的手机号码
weComMsg.addMentionedMobileNo("123412341234");
WeComPusher.push(weComMsg,"https://企业微信机器人地址");
```



# changelog

# 5.0.0-SNAPSHOT.20220408

* 首次发布支持企业微信机器人推送
