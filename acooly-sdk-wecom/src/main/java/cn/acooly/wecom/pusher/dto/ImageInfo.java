/**
 * acooly-pm
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-02-21 13:47
 */
package cn.acooly.wecom.pusher.dto;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;

/**
 * @author zhangpu
 * @date 2022-02-21 13:47
 */
@Data
public class ImageInfo extends InfoBase {

    /**
     * 图片内容的base64编码
     */
    private String base64;
    /**
     * 图片内容（base64编码前）的md5值
     */
    private String md5;
}
