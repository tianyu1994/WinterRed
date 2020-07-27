import org.codeforworld.winterredserver.blockChain.Agent;
import org.codeforworld.winterredserver.blockChain.Block;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 区块链测试类
 */
public class AgentTest {
    public static final Block root = new Block(0, "ROOT_HASH", "ROOT");
    public List<Agent> agents = new ArrayList<>();


    @Test
    public void getBlockchain() {

    }

    @Test
    public void createBlockTest() throws IOException {
        Agent a = new Agent("localhost",root, agents);
        Block block = a.createBlock();
        block.setData("1234");
        a.addBlock(block);
        List<Block> blockchain = a.getBlockchain();

        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("C:/Users/ImSherlocked/Desktop/winterRed/WinterRed/WinterRedServer/src/main/resources/data/blockchain.txt")));
        oo.writeObject(blockchain);
        System.out.println("blockchain对象序列化成功！");
        oo.close();

        System.out.println(blockchain);
    }

    @Test
    public void addBlock() {
    }

    @Test
    public void deserializeTest() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("C:/Users/ImSherlocked/Desktop/winterRed/WinterRed/WinterRedServer/src/main/resources/data/blockchain.txt")));
        List<Block> blockchain = (List<Block>) ois.readObject();
        System.out.println(blockchain);
        System.out.println("Person对象反序列化成功！");
    }
}