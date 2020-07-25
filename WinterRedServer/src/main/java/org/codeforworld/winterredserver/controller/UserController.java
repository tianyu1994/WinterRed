package org.codeforworld.winterredserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.User;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.UserService;
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
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/queryByPage")
    public Result queryByPage (@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, User user) {
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<User> list = userService.queryUser(user);
        PageInfo<User> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody User user){
        Result result = new Result();
        boolean isSuccess = userService.saveOrUpdate(user);
        if(isSuccess){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete (User user){
        Result result = new Result();
        boolean isSuccess = userService.removeById(user.getId());
        if (isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}
