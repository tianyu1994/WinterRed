package org.codeforworld.winterredserver.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.lang.CodeConstant;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.request.SubscribeRequest;
import org.codeforworld.winterredserver.service.SubscribeUserService;
import org.codeforworld.winterredserver.service.UserFieldRelationService;
import org.codeforworld.winterredserver.utils.IdentifyingCodeUtils;
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
    public boolean saveOrUpdateByemail(String email, List<Integer> professionalFieldIdList)  throws IOException {
        boolean isSuccess = true;
        for(Integer professionalFieldId : professionalFieldIdList){
            Result result = subscribeUserService.saveOrUpdateByemail(email, professionalFieldId);
            if(result.getRetCode() != CodeConstant.RETCODE_200){
                isSuccess = false;
            }
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
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @PostMapping ("/checkIdentifyingCode")
    public Result checkIdentifyingCode(@RequestBody SubscribeRequest subscribeRequest)  throws IOException {
        Result result = new Result();
        //更新验证码配置文件
        IdentifyingCodeUtils identifyingCodeUtils = new IdentifyingCodeUtils();
        String email = subscribeRequest.getEmail();
        String identifyingCode = subscribeRequest.getIdentifyingCode();
        boolean isSuccess = true;
        if (identifyingCodeUtils.checkFile(email, identifyingCode)){
            if (subscribeRequest.getProfessionalFieldIdList() != null && subscribeRequest.getProfessionalFieldIdList().size() > 0){
                List<Integer> professionalFieldIdList = subscribeRequest.getProfessionalFieldIdList();
                for(Integer professionalFieldId : professionalFieldIdList){
                    result = subscribeUserService.saveOrUpdateByemail(email,professionalFieldId);
                    if(result.getRetCode() != CodeConstant.RETCODE_200){
                        isSuccess = false;
                    }
                }
            }
            else {
                result.setFailedMsg("您没有选择订阅的领域");
                return result;
            }
        } else {
            result.setFailedMsg("验证码不正确, 请重新输入");
            return result;
        }
        if(isSuccess){
            result.setSuccessMsg("订阅成功");
        }else {
            result.setFailedMsg("订阅失败");
        }
        return result;
    }
}
