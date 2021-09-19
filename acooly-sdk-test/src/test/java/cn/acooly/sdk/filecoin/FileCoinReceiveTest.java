/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 15:30
 */
package cn.acooly.sdk.filecoin;

import cn.acooly.sdk.filecoin.domain.FilMessage;
import cn.acooly.sdk.filecoin.domain.SecpkMessage;
import cn.acooly.sdk.filecoin.rpclient.FileCoinChain;
import cn.acooly.sdk.filecoin.rpclient.message.ChainGetBlockMessagesRpcResponse;
import cn.acooly.sdk.filecoin.rpclient.message.ChainGetTipSetByHeightRpcResponse;
import com.acooly.core.utils.Collections3;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangpu
 * @date 2021-06-23 15:30
 */
@Slf4j
public class FileCoinReceiveTest extends FileCoinRpcBaseTest {

    private static FileCoinChain fileCoinChain = new FileCoinChain(transport);

    private List<String> addressList = Lists.newArrayList("t1fdqsh5pxg5jqi3mzpqqvdfj26rfyosdpbuvh3ni",
            "");


    @Test
    public void receive() {
        Integer blockHeight = 196698;//196690;
        if (blockHeight == null) {
            blockHeight = getBlockHeight();
        }
        List<String> cids = chainGetTipSetByHeight(blockHeight);
        List<FilMessage> filMessages = Lists.newArrayList();
        for (String cid : cids) {
            filMessages.addAll(chainGetBlockMessages(cid));
        }
        filMessages = filMessages.stream().distinct().collect(Collectors.toList());

        System.out.println();
        List<FilMessage> result = Lists.newArrayList();
        for (FilMessage filMessage : filMessages) {
            if (addressList.contains(filMessage.getTo())) {
                result.add(filMessage);
                System.out.println(JSON.toJSON(filMessage));
            }
        }

        if (Collections3.isEmpty(result)) {
            return;
        }

        FilMessage filMessage = Collections3.getFirst(result);
        log.info("FilMessage Amount: {} FIL", filMessage.getAmount());

    }


    /**
     * 第一步：查询当前区块高度
     */
    public Integer getBlockHeight() {
        Integer height = fileCoinChain.getBlockHeight();
        log.info("1、Query Block Height: {}", height);
        return height;
    }

    /**
     * 第二步：根据区块高度查询CIDs
     */
    public List<String> chainGetTipSetByHeight(Integer blockHeight) {
        ChainGetTipSetByHeightRpcResponse.ChainGetTipSetByHeight chainGetTipSetByHeight
                = fileCoinChain.chainGetTipSetByHeight(blockHeight);
        log.info("2、读取指定高度的TipSet获得CIDs. ChainGetTipSetByHeight : blockHeight:{}, result: {}", blockHeight, chainGetTipSetByHeight);
        return chainGetTipSetByHeight.getFilCids();
    }


    public List<FilMessage> chainGetBlockMessages(String cid) {
        ChainGetBlockMessagesRpcResponse.ChainGetBlockMessages chainGetBlockMessages
                = fileCoinChain.chainGetBlockMessages(cid);
        log.info("3、ChainGetBlockMessages cid:{}, result: {}", cid, chainGetBlockMessages);

        List<FilMessage> filMessages = Lists.newArrayList();
//        filMessages.addAll(chainGetBlockMessages.getBlsMessages());
        for (SecpkMessage secpkMessage : chainGetBlockMessages.getSecpkMessages()) {
            filMessages.add(secpkMessage.getFilMessage());
        }
        return filMessages;
    }

}
