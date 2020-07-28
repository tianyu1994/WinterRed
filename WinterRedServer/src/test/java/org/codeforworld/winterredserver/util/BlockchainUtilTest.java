package org.codeforworld.winterredserver.util;

import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.utils.BlockchainUtil;
import org.junit.Test;

public class BlockchainUtilTest {

    @Test
    public void gainCheckManInfo() {

    }

    @Test
    public void gainRumorInfo() {
        RumorInfo rumorInfo = BlockchainUtil.gainRumorInfo(3);
        System.out.println(rumorInfo);
    }

    @Test
    public void gainBatchRumorInfo() {
        BlockchainUtil.gainBatchRumorInfo();
    }

    @Test
    public void pushRumorInfo2Chain() {
        RumorInfo rumorInfo = new RumorInfo();
        rumorInfo.setId(3);
        rumorInfo.setAskUserId(4);
        rumorInfo.setBody("你好你好你好你好");
        BlockchainUtil.pushRumorInfo2Chain(rumorInfo);

    }
}