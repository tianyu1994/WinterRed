package org.codeforworld.winterredserver.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.SubscribeUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  订阅用户表 前端控制器
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/subscribeUser")
@CrossOrigin
@Slf4j
public class SubscribeUserController {
    @Resource
    private SubscribeUserService subscribeUserService;

    @GetMapping("/queryByPage")
    public Result queryByPage (@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, SubscribeUser subscribeUser) {
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<SubscribeUser> list = subscribeUserService.querySubscribeUser(subscribeUser);
        PageInfo<SubscribeUser> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody @Valid SubscribeUser subscribeUser){
        Result result = subscribeUserService.saveOrUpdateSubscribeUser(subscribeUser);
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete (SubscribeUser subscribeUser){
        Result result = new Result();
        boolean isSuccess = subscribeUserService.removeById(subscribeUser.getId());
        if (isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}
