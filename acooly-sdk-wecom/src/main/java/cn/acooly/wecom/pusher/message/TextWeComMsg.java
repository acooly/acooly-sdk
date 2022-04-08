/**
 * acooly-pm
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-02-21 13:51
 */
package cn.acooly.wecom.pusher.message;

import cn.acooly.wecom.pusher.dto.TextInfo;
import com.acooly.core.utils.Strings;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文本类型消息
 * <p>
 * text和markdown
 *
 * @author zhangpu
 * @date 2022-02-21 13:51
 */
@Data
@NoArgsConstructor
public class TextWeComMsg extends WeComMsg {

    /**
     * 文本消息
     */
    private TextInfo text = new TextInfo();

    public TextInfo getText() {
        return text;
    }

    public TextWeComMsg(String content) {
        setMsgtype(WeComMsgType.text);
        this.text.setContent(content);
    }

    public TextWeComMsg(String content, String mobileNo) {
        this(content);
        if (Strings.isNotBlank(mobileNo)) {
            addMentionedMobileNo(mobileNo);
        }
    }

    public TextWeComMsg(String content, List<String> mobileNos) {
        this(content);
        this.text.setMentionedMobileList(mobileNos);
    }

    public void addMentionedMobileNo(String mobileNo) {
        this.text.getMentionedMobileList().add(mobileNo);
    }

}
