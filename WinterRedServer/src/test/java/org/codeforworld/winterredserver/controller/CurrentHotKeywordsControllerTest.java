package org.codeforworld.winterredserver.controller;

import org.codeforworld.winterredserver.WinterRedServerStartUp;
import org.codeforworld.winterredserver.entity.CurrentHotKeywords;
import org.codeforworld.winterredserver.lang.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WinterRedServerStartUp.class)
public class CurrentHotKeywordsControllerTest {
    @Resource
    CurrentHotKeywordsController currentHotKeywordsController;
    @Test
    public void queryAll() {
        Result result = currentHotKeywordsController.queryAll();
        System.out.println("================="+result);
        Assert.assertTrue(!((List)result.getResults()).isEmpty());

    }
}