package org.codeforworld.winterredserver.blockChain;

import org.codeforworld.winterredserver.util.BlockchainUtil;
import org.codeforworld.winterredserver.utils.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 对区块链的代理类
 *
 * @author: kfzx-menghj
 * @Time: 2020/7/27  16:20
 */

public class Agent {
    private String name;
    private List<Agent> peers;
    private List<Block> blockchain = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(Agent.class);


    // for jackson
    public Agent() {
    }

    public Agent(final String name, final Block root, final List<Agent> agents) {
        this.name = name;
        this.peers = agents;
        blockchain.add(root);
    }

    public String getName() {
        return name;
    }


    public List<Block> getBlockchain() {
        return blockchain;
    }

    public Block createBlock() {
        if (blockchain.isEmpty()) {
            return null;
        }

        Block previousBlock = getLatestBlock();
        if (previousBlock == null) {
            return null;
        }

        final int index = previousBlock.getIndex() + 1;
        final Block block = new Block(index, previousBlock.getHash(), name);
        System.out.println(String.format("%s created new block %s", name, block.toString()));
//        broadcast(INFO_NEW_BLOCK, block);
        return block;
    }

    public void addBlock(Block block) {
        if (isBlockValid(block)) {
            blockchain.add(block);
        }
    }

    public Block getLatestBlock() {
        if (blockchain.isEmpty()) {
            return null;
        }
        return blockchain.get(blockchain.size() - 1);
    }

    public boolean isBlockValid(final Block block) {
        final Block latestBlock = getLatestBlock();
        if (latestBlock == null) {
            return false;
        }
        final int expected = latestBlock.getIndex() + 1;
        if (block.getIndex() != expected) {
            System.out.println(String.format("Invalid index. Expected: %s Actual: %s", expected, block.getIndex()));
            return false;
        }
        if (!Objects.equals(block.getPreviousHash(), latestBlock.getHash())) {
            System.out.println("Unmatched hash code");
            return false;
        }
        return true;
    }

    public boolean writeBlockChain() {
        try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                    new File("src/main/resources/blockchain.txt")));
            oo.writeObject(blockchain);
            oo.close();
            logger.info("blockchain序列化存储成功！");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("blockchain序列化存储失败！");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("blockchain序列化存储失败！");
        }
        return false;
    }

    public boolean readBlockChain() {
        List<Block> newBlockchain = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                    new File("src/main/resources/blockchain.txt")));
            blockchain = (List<Block>) ois.readObject();
            logger.info("blockchain反序列化取得成功！");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


}
