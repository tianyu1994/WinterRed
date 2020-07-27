package org.codeforworld.winterredserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.CheckPlat;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.CheckPlatService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 核查平台表 前端控制器
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/checkPlat")
@CrossOrigin
@Slf4j
public class CheckPlatController {
    @Resource
    private CheckPlatService checkPlatService;

    @GetMapping("/queryByPage")
    public Result queryByPage(@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, CheckPlat checkPlat){
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<CheckPlat> list = checkPlatService.queryCheckPlat(checkPlat);
        PageInfo<CheckPlat> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate (@RequestBody @Valid CheckPlat checkPlat) {
        Result result = checkPlatService.saveOrUpdateCheckPlat(checkPlat);
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete (CheckPlat checkPlat){
        Result result = new Result();
        boolean isSuccess = checkPlatService.removeById(checkPlat.getId());
        if(isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}
