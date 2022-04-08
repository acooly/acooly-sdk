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
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * markdown类型消息主体
 *
 * @author zhangpu
 * @date 2022-02-21 14:02
 */
@Data
public class MarkdownInfo extends InfoBase {
    /**
     * markdown内容，最长不超过4096个字节，必须是utf8编码
     */
    @NotBlank
    @Size(max = 4096)
    private String content;
}
