package org.codeforworld.winterredserver.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.CheckmanFieldRelation;
import org.codeforworld.winterredserver.lang.Page;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.CheckManService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 核查人员表 前端控制器
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/checkMan")
@CrossOrigin
@Slf4j
public class CheckManController {

    @Resource
    private CheckManService checkManService;

    @GetMapping("/queryByPage")
    public Result queryByPage (@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, CheckMan checkMan) {
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<CheckMan> list = checkManService.queryCheckMan(checkMan);
        PageInfo<CheckMan> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(CheckMan checkMan){
        Result result = new Result();
        boolean isSuccess = checkManService.saveOrUpdate(checkMan);
        if(isSuccess){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete (CheckMan checkMan){
        Result result = new Result();
        boolean isSuccess = checkManService.removeById(checkMan.getId());
        if (isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}
