package org.codeforworld.winterredserver.controller;

import org.codeforworld.winterredserver.WinterRedServerStartUp;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.lang.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WinterRedServerStartUp.class)
public class RumorInfoControllerTest {
    @Resource
    RumorInfoController rumorInfoController;

    @Test
    public void queryRolledRumorInfoById() {
        Result result = rumorInfoController.queryRolledRumorInfoById(5);
        System.out.println("============================" + result);
        Assert.assertNotNull(result.getResults());

    }
}