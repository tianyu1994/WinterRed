package org.codeforworld.winterredserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.entity.UserFieldRelation;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.UserFieldRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
@Slf4j
public class UserFieldRelationController {

    @Resource
    private UserFieldRelationService userFieldRelationService;

    @GetMapping("/queryAll")
    public Result queryAll(UserFieldRelation userFieldRelation){
        Result result = new Result();
        List<UserFieldRelation> list = userFieldRelationService.queryUserFieldRelation(userFieldRelation);
        result.setResults(list);
        return result;
    }

    @GetMapping("/queryByPage")
    public Result queryByPage(@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize,  UserFieldRelation userFieldRelation){
        Result result = new Result();

        PageHelper.startPage(curPage, pageSize);
        List<UserFieldRelation> list = userFieldRelationService.queryUserFieldRelation(userFieldRelation);
        PageInfo<UserFieldRelation> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @GetMapping("/queryByProfessionalFieldId")
    public Result queryByProfessionalFieldId(@RequestParam("professionalFieldId") Integer professionalFieldId){
        Result result = new Result();
        List<SubscribeUser> list = userFieldRelationService.queryByProfessionalFieldId(professionalFieldId);
        result.setResults(list);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody @Valid UserFieldRelation userFieldRelation){
        Result result = userFieldRelationService.saveOrUpdateUserFieldRelation(userFieldRelation);
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete (UserFieldRelation userFieldRelation){
        Result result = new Result();
        boolean isSuccess = userFieldRelationService.removeById(userFieldRelation.getId());
        if (isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}
