package org.codeforworld.winterredserver.controller;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.entity.UserFieldRelation;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.SubscribeUserService;
import org.codeforworld.winterredserver.service.UserFieldRelationService;
import org.codeforworld.winterredserver.util.IdentifyingCodeUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
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
    @Resource
    private UserFieldRelationService userFieldRelationService;

    @GetMapping("/queryAll")
    public Result queryAll (SubscribeUser subscribeUser) {
        Result result = new Result();
        List<SubscribeUser> list = subscribeUserService.querySubscribeUser(subscribeUser);
        result.setResults(list);
        return result;
    }


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


    /**
     * 更新用户订阅
     * @param email
     * @param professionalFieldIdList
     * @return
     */
    @PostMapping("/saveOrUpdateByemail")
    public Boolean saveOrUpdateByemail(String email, List<Integer> professionalFieldIdList)  throws IOException {

        //查邮箱是否保存
        QueryWrapper<SubscribeUser> queryWrapper = new QueryWrapper<SubscribeUser>();
        queryWrapper.eq("email",email);
        int i = subscribeUserService.count(queryWrapper);
        Integer id = null;
        if (i == 0){
            SubscribeUser sub = new SubscribeUser();
            sub.setEmail(email);
            saveOrUpdate(sub);
            id = sub.getId();
        }
        else {
            SubscribeUser subscribeUser = subscribeUserService.getOne(queryWrapper);
            id = subscribeUser.getId();
        }
        boolean isSuccess = false;
        for (Integer professionalFieldId : professionalFieldIdList){
            UserFieldRelation userFieldRelation = new UserFieldRelation();
            userFieldRelation.setUserId(id);
            userFieldRelation.setProfessionalFieldId(professionalFieldId);
            isSuccess = userFieldRelationService.saveOrUpdate(userFieldRelation);
        }

        return isSuccess;
    }

    @GetMapping("/sendEmail")
    public Result sendEmail(String email) throws Exception{
        Result result = new Result();
        //产生6位随机数验证码
        String identifyingCode = String.valueOf((int) (Math.random()*9+1)*167894);
        //发送邮件
        IdentifyingCodeUtils identifyingCodeUtils = new IdentifyingCodeUtils();
        identifyingCodeUtils.sendEmail(email,identifyingCode);
        //更新验证码配置文件
        boolean isSuccess = identifyingCodeUtils.updateFile(email,identifyingCode);

        if(isSuccess){
            result.setSuccessMsg("获取验证码成功");
        }else {
            result.setFailedMsg("获取验证码失败");
        }
        return result;
    }

    @PostMapping("/checkIdentifyingCode")
    public Result checkIdentifyingCode(String email, String identifyingCode, @RequestBody List<Integer> professionalFieldIdList)  throws IOException {
        Result result = new Result();
        //更新验证码配置文件
        IdentifyingCodeUtils identifyingCodeUtils = new IdentifyingCodeUtils();

        boolean isSuccess = false;
        if (identifyingCodeUtils.checkFile(email, identifyingCode)){
            isSuccess = saveOrUpdateByemail(email,professionalFieldIdList);
        }
        if(isSuccess){
            result.setSuccessMsg("订阅成功");
        }else {
            result.setFailedMsg("订阅失败");
        }
        return result;
    }
}
