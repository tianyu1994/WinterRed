package org.codeforworld.winterredserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.codeforworld.winterredserver.entity.UserFieldRelation;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.UserFieldRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订阅用户领域关系表 前端控制器
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/userFieldRelation")
@CrossOrigin
public class UserFieldRelationController {

    @Resource
    private UserFieldRelationService userFieldRelationService;

    @GetMapping("/queryByPage")
    public Result UserFieldRelationService(@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize,  UserFieldRelation userFieldRelation){
        Result result = new Result();

        PageHelper.startPage(curPage, pageSize);
        List<UserFieldRelation> list = userFieldRelationService.queryUserFieldRelation(userFieldRelation);
        PageInfo<UserFieldRelation> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }
}
