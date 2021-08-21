/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-26 12:14
 */
package cn.acooly.sdk.filecoin.ecc;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-07-26 12:14
 */
@Slf4j
public class ECTest {

    public static void main(String[] argu) throws Exception {
        Map<String, String> map = ECC.generateKey();
        String privKey = map.get(ECCEnum.PRIVATE_KEY.value());
        String pubKey = map.get(ECCEnum.PUBLIC_KEY.value());
        System.out.println("私钥：" + privKey);
        System.out.println("公钥：" + pubKey);
        String text = "java ECC 加密、解密算法，如果写的有问题，请大家踊跃评论，谢谢！";
        byte[] b = ECC.encrypt(text.getBytes("UTF-8"), pubKey);
        String str = BASE64Encoder.encodeBuffer(b);
        System.out.println("密文：" + str);
        String outputStr = new String(ECC.decrypt(b, privKey));
        System.out.println("原始文本：" + text);
        System.out.println("解密文本：" + outputStr);
    }

}
