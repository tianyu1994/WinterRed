package org.codeforworld.winterredserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.AskUser;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.enumType.CheckStatus;
import org.codeforworld.winterredserver.enumType.RumorSource;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.AskUserService;
import org.codeforworld.winterredserver.service.RumorInfoService;
import org.codeforworld.winterredserver.service.SubscribeUserService;
import org.codeforworld.winterredserver.service.UserFieldRelationService;
import org.codeforworld.winterredserver.utils.IdentifyingCodeUtils;
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
    @Resource
    private AskUserService askUserService;
    @Resource
    private SubscribeUserService subscribeUserService;
    @Resource
    private UserFieldRelationService userFieldRelationService;

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
        Result result = new Result();
        try {
            Integer askUserId = rumorInfo.getAskUserId();
            AskUser askUser = askUserService.getById(askUserId);
            //更新验证码配置文件
            IdentifyingCodeUtils identifyingCodeUtils = new IdentifyingCodeUtils();
            if (identifyingCodeUtils.checkFile(askUser.getEmail(), rumorInfo.getIdentifyingCode())){
                subscribeUserService.saveOrUpdateByemail(askUser.getEmail(),rumorInfo.getProfessionalFieldId());
            } else {
                result.setFailedMsg("验证码错误!");
                return result;
            }

            if(!RumorSource.getAllNames().contains(rumorInfo.getSource())){
                result.setErrorMsg("信息来源渠道必须为：" + RumorSource.getAllNames().toString() + "中的一种！");
                return result;
            }
            if(!CheckStatus.getAllNames().contains(rumorInfo.getStatus())){
                result.setErrorMsg("辟谣状态必须为：" + CheckStatus.getAllNames().toString() + "中的一种！");
                return result;
            }
            result = rumorInfoService.saveOrUpdateRumorInfo(rumorInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("保存谣言信息发生异常", e);
            result.setErrorMsg("保存谣言信息发生异常");
        }
        return result;
    }

    /**
     * 保存核查信息
     * @param rumorInfo
     * @return
     */
    @PostMapping("/saveOrUpdateCheck")
    public Result saveOrUpdateCheck(@RequestBody @Valid RumorInfo rumorInfo) {
        Result result = new Result();
        if(!RumorSource.getAllNames().contains(rumorInfo.getSource())){
            result.setErrorMsg("信息来源渠道必须为：" + RumorSource.getAllNames().toString() + "中的一种！");
            return result;
        }
        if(!CheckStatus.getAllNames().contains(rumorInfo.getStatus())){
            result.setErrorMsg("辟谣状态必须为：" + CheckStatus.getAllNames().toString() + "中的一种！");
            return result;
        }
        result = rumorInfoService.saveOrUpdateRumorInfoCheck(rumorInfo);
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
    public Result queryById(@RequestParam("id") Integer id) {
        Result result = new Result();
        RumorInfo rumorInfo = rumorInfoService.queryById(id);
        result.setResults(rumorInfo);
        return result;
    }

    /**
     * 根据id查记录
     * @param id
     * @return
     */
    @GetMapping("/queryRumorInfoById")
    public Result queryRumorInfoById(String id) {
        Result result = new Result();
        RumorInfo rumorInfo = rumorInfoService.queryRumorInfoById(id);
        result.setResults(rumorInfo);
        return result;
    }
}
