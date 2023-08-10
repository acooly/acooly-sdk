/**
 * acooly-components-feature
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-23 21:34
 */
package cn.acooly.sdk.aliyun.content.security.service.impl;

import cn.acooly.sdk.aliyun.content.security.domain.TextAuditExtResponse;
import cn.acooly.sdk.aliyun.content.security.service.ContentAuditService;
import cn.acooly.sdk.aliyun.content.security.domain.ImageAuditRequest;
import cn.acooly.sdk.aliyun.content.security.domain.TextAuditExtRequest;
import cn.acooly.sdk.aliyun.content.security.domain.TextAuditRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2021-07-23 21:34
 */
@Slf4j
@NoArgsConstructor
public class ContentAuditServiceMockImpl implements ContentAuditService {

	@Override
	public void textScan(TextAuditRequest request) {

	}

	@Override
	public TextAuditExtResponse textScanExt(TextAuditExtRequest request) {
		return null;
	}

	@Override
	public void imageScan(ImageAuditRequest request) {

	}

}
