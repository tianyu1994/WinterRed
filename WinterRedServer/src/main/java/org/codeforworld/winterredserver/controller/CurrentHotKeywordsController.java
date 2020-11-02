package org.codeforworld.winterredserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.AskUser;
import org.codeforworld.winterredserver.entity.CurrentHotKeywords;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.AskUserService;
import org.codeforworld.winterredserver.service.CurrentHotKeywordsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 当前热点关键词  前端控制器
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/currentHotKeywords")
@CrossOrigin
@Slf4j
public class CurrentHotKeywordsController {
    @Resource
    private CurrentHotKeywordsService currentHotKeywordsService;

    @GetMapping("/queryAll")
    public Result queryAll() {
        Result result = new Result();
        List<CurrentHotKeywords> list = currentHotKeywordsService.list();
        List<Map<String, String>> resultList = new ArrayList<>();
        for(CurrentHotKeywords currentHotKeywords: list) {
            Map<String, String> tmpMap = new HashMap<>();
            tmpMap.put("value", currentHotKeywords.getKeywords());
            resultList.add(tmpMap);
        }
        result.setResults(resultList);
        return result;
    }
}
