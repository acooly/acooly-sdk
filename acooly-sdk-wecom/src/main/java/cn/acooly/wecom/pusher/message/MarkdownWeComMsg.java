/**
 * acooly-pm
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-02-21 13:51
 */
package cn.acooly.wecom.pusher.message;

import cn.acooly.wecom.pusher.dto.MarkdownInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * markdown类型消息
 * <p>
 * text和markdown
 *
 * @author zhangpu
 * @date 2022-02-21 13:51
 */
@Data
@NoArgsConstructor
public class MarkdownWeComMsg extends WeComMsg {

    /**
     * 文本消息
     */
    private MarkdownInfo markdown = new MarkdownInfo();

    public MarkdownWeComMsg(String content) {
        setMsgtype(WeComMsgType.markdown);
        this.markdown.setContent(content);
    }

}
