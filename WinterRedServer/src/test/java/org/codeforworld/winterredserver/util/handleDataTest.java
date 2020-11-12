package org.codeforworld.winterredserver.util;

import org.codeforworld.winterredserver.WinterRedServerStartUp;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.service.CheckManService;
import org.codeforworld.winterredserver.service.CheckPlatService;
import org.codeforworld.winterredserver.service.RumorInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WinterRedServerStartUp.class)
public class handleDataTest {
    @Resource
    RumorInfoService rumorInfoService;
    @Resource
    CheckManService checkManService;
    @Resource
    CheckPlatService checkPlatService;

    @Test
    public void test_spider_data_convert_to_check_plat_data(){
        RumorInfo rumorInfo = new RumorInfo();
        List<RumorInfo> rumorInfoList = rumorInfoService.queryRumorInfo(rumorInfo);

        for (RumorInfo rumorInfo1 : rumorInfoList){
            
        }
    }

}
