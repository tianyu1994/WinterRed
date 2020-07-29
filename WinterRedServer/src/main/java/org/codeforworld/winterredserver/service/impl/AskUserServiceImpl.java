package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codeforworld.winterredserver.entity.AskUser;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.AskUserMapper;
import org.codeforworld.winterredserver.service.AskUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-27
 */
@Service
public class AskUserServiceImpl extends ServiceImpl<AskUserMapper, AskUser> implements AskUserService {
    @Resource
    private AskUserMapper askUserMapper;

    @Override
    public List<AskUser> queryAskUser(AskUser askUser) {
        List<AskUser> list = askUserMapper.queryAskUser(askUser);
        return list;
    }

    @Override
    public Result saveOrUpdateAskUser(AskUser askUser) {
        Result result = new Result();
        boolean isExists = checkUnique(askUser);
        int i = 0;
        if(isExists){
            result.setErrorMsg("邮箱已经存在，不能重复新增提问用户");
            return result;
        }
        if(askUser.getId() != null){
            i = askUserMapper.updateById(askUser);
        }else {
            i = askUserMapper.insert(askUser);
        }
        if(i > 0){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @Override
    public Integer getAskUserIdByEmail(String email) {
        AskUser askUser = new AskUser();
        askUser.setEmail(email);
        boolean isExists = checkUnique(askUser);
        if(!isExists) {
            askUserMapper.insert(askUser);
        }
        List<AskUser> askUserList = askUserMapper.queryAskUser(askUser);
        return askUserList.size() > 0 ? askUserList.get(0).getId() : null;
    }

    /**
     * 检查唯一索引
     * @param askUser
     * @return
     */
    private boolean checkUnique(AskUser askUser){
        boolean isExists = false;
        QueryWrapper<AskUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", askUser.getEmail());
        List<AskUser> askUserList = askUserMapper.selectList(queryWrapper);
        if(askUserList == null || askUserList.size() < 1){
            return isExists;
        }else if(askUser.getId() != null && askUserList.get(0).getId() == askUser.getId()){
            return isExists;
        }else {
            isExists = true;
            return isExists;
        }
    }
}
