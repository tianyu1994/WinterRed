package org.codeforworld.winterredserver.util;

import org.codeforworld.winterredserver.controller.RumorInfoController;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.RumorInfoMapper;
import org.codeforworld.winterredserver.service.RumorInfoService;
import org.codeforworld.winterredserver.service.impl.RumorInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@MapperScan("org.codeforworld.winterredserver.mapper")
public class DistributorTest {
    private static RumorInfoServiceImpl rumorInfoController = new RumorInfoServiceImpl();

    public static void main(String[] args) throws IOException {
//        Result result = rumorInfoController.queryById(1);
        RumorInfo rumorInfo = rumorInfoController.queryById(1);
//        RumorInfo rumorInfo = new RumorInfo();
//        rumorInfo.setTitle("感染HIV就是得艾滋病？世界艾滋病日，别被谣言吓着中国");
//        rumorInfo.setCheckManId(2);
//        rumorInfo.setProfessionalFieldId(4);
//        List<CheckMan> checkMan = checkRumorInfo(rumorInfo);
//        System.out.println(checkMan);
    }
}
