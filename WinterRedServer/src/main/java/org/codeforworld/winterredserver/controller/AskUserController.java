package org.codeforworld.winterredserver.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.codeforworld.winterredserver.entity.AskUser;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.AskUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/askUser")
public class AskUserController {
    @Resource
    private AskUserService askUserService;

    @GetMapping("/queryByPage")
    public Result queryByPage (@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, AskUser askUser) {
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<AskUser> list = askUserService.queryAskUser(askUser);
        PageInfo<AskUser> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody @Valid AskUser askUser){
        Result result = new Result();
        boolean isSuccess = askUserService.saveOrUpdate(askUser);
        if(isSuccess){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete (AskUser askUser){
        Result result = new Result();
        boolean isSuccess = askUserService.removeById(askUser.getId());
        if (isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}