/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-26 12:06
 */
package cn.acooly.sdk.filecoin.ecc;

import com.rfksystems.blake2b.Blake2b;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-07-26 12:06
 */
@Slf4j
@UtilityClass
public class ECC {


    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public static Map<String, String> generateKey() throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ECCEnum.ALGORITHM.value(),
                ECCEnum.PROVIDER.value());
        // 1、256位的随机数
        keyPairGenerator.initialize(256, new SecureRandom());
        KeyPair kp = keyPairGenerator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) kp.getPublic();
        ECPrivateKey privateKey = (ECPrivateKey) kp.getPrivate();
        Map<String, String> map = new HashMap<>();

        map.put(ECCEnum.PRIVATE_KEY.value(), BASE64Encoder.encodeBuffer(privateKey.getEncoded()));
        map.put(ECCEnum.PUBLIC_KEY.value(), BASE64Encoder.encodeBuffer(publicKey.getEncoded()));
        return map;
    }

    /**
     * 加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = BASE64Decoder.decodeBuffer(publicKey);

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ECCEnum.ALGORITHM.value());

        ECPublicKey pubKey = (ECPublicKey) keyFactory
                .generatePublic(x509KeySpec);

        Cipher cipher = new NullCipher();
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }


    /**
     * 解密
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = BASE64Decoder.decodeBuffer(privateKey);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ECCEnum.ALGORITHM.value());

        ECPrivateKey priKey = (ECPrivateKey) keyFactory
                .generatePrivate(pkcs8KeySpec);

        Cipher cipher = new NullCipher();
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        return cipher.doFinal(data);
    }

//    private static String byteToAddress(byte[] pub) {
//        Blake2b.Digest digest = Blake2b.Digest.newInstance(20);
//        String hash = HexUtil.encodeHexStr(digest.digest(pub));
//        //将得到的blake2哈希值前添加0x01
//        String pubKeyHash = "01" + HexUtil.encodeHexStr(digest.digest(pub));
//        //用blake2b算法计算4位校验和
//        Blake2b.Digest blake2b3 = Blake2b.Digest.newInstance(4);
//        String checksum = HexUtil.encodeHexStr(blake2b3.digest(HexUtil.decodeHex(pubKeyHash)));
//        //Base32编码
//        return "f1" + Base32.encode(HexUtil.decodeHex(hash + checksum)).toLowerCase();
//    }

}