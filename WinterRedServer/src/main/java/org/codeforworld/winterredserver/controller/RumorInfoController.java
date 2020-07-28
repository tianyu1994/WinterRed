package org.codeforworld.winterredserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.RumorInfoService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
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

    /**
     * 查询所有记录
     * @param rumorInfo
     * @return
     */
    @GetMapping("/queryAll")
    public Result queryAll(RumorInfo rumorInfo) {
        Result result = new Result();
        List<RumorInfo> list = rumorInfoService.queryRumorInfo(rumorInfo);
        result.setResults(list);
        return result;
    }


    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @param rumorInfo
     * @return
     */
    @GetMapping("/queryByPage")
    public Result queryByPage(@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, RumorInfo rumorInfo) {
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<RumorInfo> list = rumorInfoService.queryRumorInfo(rumorInfo);
        PageInfo<RumorInfo> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    /**
     * 保存
     * @param rumorInfo
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody @Valid RumorInfo rumorInfo) {
        Result result = rumorInfoService.saveOrUpdateRumorInfo(rumorInfo);
        return result;
    }


    /**
     * 更新核查状态
     * @param rumorInfo
     * @return
     */
    @PostMapping("/modifyStatus")
    public Result modifyStatus(@RequestBody RumorInfo rumorInfo) {
        Result result = rumorInfoService.modifyStatus(rumorInfo);
        return result;
    }

    /**
     * 删除
     * @param rumorInfo
     * @return
     */
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

    /**
     * 根据id查记录
     * @param id
     * @return
     */
    @GetMapping("/queryById")
    public Result queryById(String id) {
        Result result = new Result();
        RumorInfo rumorInfo = rumorInfoService.queryRumorInfoById(id);
        result.setResults(rumorInfo);
        return result;
    }
}
