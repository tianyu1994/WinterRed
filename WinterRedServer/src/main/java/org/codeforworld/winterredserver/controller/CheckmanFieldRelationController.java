package org.codeforworld.winterredserver.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.CheckmanFieldRelation;
import org.codeforworld.winterredserver.lang.Page;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.CheckmanFieldRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 核查人员领域关系表 前端控制器
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/checkmanFieldRelation")
@CrossOrigin
@Slf4j
public class CheckmanFieldRelationController {
    @Resource
    private CheckmanFieldRelationService checkmanFieldRelationService;

    @GetMapping("/queryByPage")
    public Result queryByPage(@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, CheckmanFieldRelation checkmanFieldRelation){
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<CheckmanFieldRelation> list = checkmanFieldRelationService.queryCheckManFieldRelation(checkmanFieldRelation);
        PageInfo<CheckmanFieldRelation> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate (CheckmanFieldRelation checkmanFieldRelation) {
        Result result = new Result();
        boolean isSuccess = checkmanFieldRelationService.saveOrUpdate(checkmanFieldRelation);
        if(isSuccess){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete (CheckmanFieldRelation checkmanFieldRelation){
        Result result = new Result();
        boolean isSuccess = checkmanFieldRelationService.removeById(checkmanFieldRelation.getId());
        if(isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}
