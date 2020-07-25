package org.codeforworld.winterredserver.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.RumorInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 谣言信息表 前端控制器
 * </p>
 *
 * @author kfzx-menghj
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/rumorInfo")
@CrossOrigin
@Slf4j
public class RumorInfoController {
    @Resource
    private RumorInfoService rumorInfoService;

    @GetMapping("/queryByPage")
    public Result queryByPage(@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, RumorInfo rumorInfo) {
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<RumorInfo> list = rumorInfoService.queryRumorInfo(rumorInfo);
        PageInfo<RumorInfo> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RumorInfo rumorInfo) {
        Result result = new Result<>();
        boolean isSuccess = rumorInfoService.saveOrUpdate(rumorInfo);
        if(isSuccess){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete(RumorInfo rumorInfo) {
        Result result = new Result<>();
        boolean isSuccess = rumorInfoService.removeById(rumorInfo.getId());
        if(isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}
