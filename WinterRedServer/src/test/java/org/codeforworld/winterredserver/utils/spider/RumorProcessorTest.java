package org.codeforworld.winterredserver.utils.spider;

import org.codeforworld.winterredserver.WinterRedServerStartUp;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WinterRedServerStartUp.class)
public class RumorProcessorTest {

    @Test
    public void run() {
        RumorProcessor.run();
    }
}