package org.codeforworld.winterredserver.utils;

import org.codeforworld.winterredserver.blockChain.Agent;
import org.codeforworld.winterredserver.blockChain.Block;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.service.RumorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: kfzx-menghj
 * @Time: 2020/7/28  10:37
 */
@Component
public class BlockchainUtil {
    @Autowired
    private RumorInfoService rumorInfoService;

    private static List<Agent> agents = new ArrayList<>();
    private static Block root = new Block(0, "ROOT_HASH", "ROOT");
    private static Agent a = new Agent("localhost", root, agents);

    public static BlockchainUtil blockchainUtil;

    @PostConstruct
    public void init() {
        blockchainUtil = this;
        blockchainUtil.rumorInfoService = this.rumorInfoService;
    }


    static {
        /**
         * 这一步读链后a的成员blockChain已经被赋值了，
         * 如果读链没值就是false，也不影响a的成员为初始化的区块链
         */
        a.readBlockChain();
    }


    /**
     * 从链上拿到所有checkMan的信息
     *
     * @return
     */
    public static List<CheckMan> gainCheckManInfo() {
        List<CheckMan> checkManList = new ArrayList<>();
        List<Block> blockchain = a.getBlockchain();
        for (Block block : blockchain) {
            Object data = block.getData();
            if (CheckMan.class.equals(data.getClass())) {
                checkManList.add((CheckMan) data);
            }
        }
        return checkManList;
    }

    /**
     * 在链上匹配一条rumorInfo
     *
     * @param infoId rumorId
     * @return
     */
    public static RumorInfo gainRumorInfo(Integer infoId) {
        List<Block> blockchain = a.getBlockchain();
        for (Block block : blockchain) {
            Object data = block.getData();
            if (null != data) {
                if (RumorInfo.class.equals(data.getClass())) {
                    RumorInfo rumorInfo = (RumorInfo) data;
                    if (infoId.equals(rumorInfo.getId())) {
                        return rumorInfo;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 从链上同步其他节点的rumorInfo到本地的数据库
     *
     * @return
     */
    public static List<RumorInfo> gainBatchRumorInfo() {
        List<RumorInfo> rumorInfoList = new ArrayList<>();
        //此处拿到暴力直接存
        List<Block> blockchain = a.getBlockchain();
        for (Block block : blockchain) {
            Object data = block.getData();
            if (null != data) {
                if (RumorInfo.class.equals(data.getClass())) {
                    RumorInfo rumorInfo = (RumorInfo) data;
                    rumorInfoList.add(rumorInfo);

                    try {
                        blockchainUtil.rumorInfoService.insert(rumorInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return rumorInfoList;
    }

    public static void pushRumorInfo2Chain(RumorInfo rumorInfo) {
        Block block = a.createBlock();
        block.setData(rumorInfo);
        a.addBlock(block);

        a.writeBlockChain();
    }


    public static void pushCheckManInfo2Chain(CheckMan checkMan) {
        Block block = a.createBlock();
        block.setData(checkMan);
        a.addBlock(block);

        a.writeBlockChain();
    }
}
