package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.SubscribeUserMapper;
import org.codeforworld.winterredserver.service.SubscribeUserService;
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
public class SubscribeUserServiceImpl extends ServiceImpl<SubscribeUserMapper, SubscribeUser> implements SubscribeUserService {
    @Resource
    private SubscribeUserMapper subscribeUserMapper;

    @Override
    public List<SubscribeUser> querySubscribeUser(SubscribeUser subscribeUser) {
        List<SubscribeUser> list = subscribeUserMapper.querySubscribeUser(subscribeUser);
        return list;
    }

    @Override
    public Result saveOrUpdateSubscribeUser(SubscribeUser subscribeUser) {
        Result result = new Result();
        boolean isExists = checkUnique(subscribeUser);
        int i = 0;
        if(isExists){
            result.setErrorMsg("邮箱已经存在，不能重复新增订阅用户！");
            return result;
        }
        if(subscribeUser.getId() != null){
            i = subscribeUserMapper.updateById(subscribeUser);
        }else {
            i = subscribeUserMapper.insert(subscribeUser);
        }
        if(i > 0){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    /**
     * 检查唯一索引
     * @param subscribeUser
     * @return
     */
    private boolean checkUnique(SubscribeUser subscribeUser){
        boolean isExists = false;
        QueryWrapper<SubscribeUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", subscribeUser.getEmail());
        List<SubscribeUser> askUserList = subscribeUserMapper.selectList(queryWrapper);
        if(askUserList == null || askUserList.size() < 1){
            return isExists;
        }else if(subscribeUser.getId() != null && askUserList.get(0).getId() == subscribeUser.getId()){
            return isExists;
        }else {
            isExists = true;
            return isExists;
        }
    }
}
