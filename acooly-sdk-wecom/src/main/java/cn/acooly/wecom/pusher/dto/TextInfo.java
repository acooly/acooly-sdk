/**
 * acooly-pm
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-02-21 14:02
 */
package cn.acooly.wecom.pusher.dto;

import com.acooly.core.common.facade.InfoBase;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author zhangpu
 * @date 2022-02-21 14:02
 */
@Data
public class TextInfo extends InfoBase {
    /**
     * 文本内容，最长不超过2048个字节，必须是utf8编码
     */
    @NotBlank
    @Size(max = 2048)
    private String content;

    /**
     * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userid，可以使用mentioned_mobile_list
     */
    @JSONField(name = "mentioned_list")
    private List<String> mentionedList;

    /**
     * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
     */
    @JSONField(name = "mentioned_mobile_list")
    private List<String> mentionedMobileList = Lists.newArrayList();


}
