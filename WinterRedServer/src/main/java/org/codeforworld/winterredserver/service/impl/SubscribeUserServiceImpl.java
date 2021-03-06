package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.entity.UserFieldRelation;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.SubscribeUserMapper;
import org.codeforworld.winterredserver.mapper.UserFieldRelationMapper;
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
    @Resource
    private UserFieldRelationMapper userFieldRelationMapper;

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

    @Override
    public Result saveOrUpdateByemail(String email, Integer professionalFieldId) {
        Result result = new Result();
        UserFieldRelation userFieldRelation = new UserFieldRelation();
        //查邮箱是否保存
        SubscribeUser param = new SubscribeUser();
        param.setEmail(email);
        int i = subscribeUserMapper.countSubscribeUser(param);
        Integer id = null;
        if (i == 0){
            SubscribeUser sub = new SubscribeUser();
            sub.setEmail(email);
            saveOrUpdate(sub);
            id = sub.getId();
        } else {
            List<SubscribeUser> subscribeUserList = subscribeUserMapper.querySubscribeUser(param);
            if(subscribeUserList != null && subscribeUserList.size() > 0){
                id = subscribeUserList.get(0).getId();
            }
        }
        userFieldRelation.setUserId(id);
        userFieldRelation.setProfessionalFieldId(professionalFieldId);
        QueryWrapper<UserFieldRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("professional_field_id", professionalFieldId);
        queryWrapper.eq("user_id", id);
        userFieldRelationMapper.delete(queryWrapper);
        int count = userFieldRelationMapper.insert(userFieldRelation);
        result.setResults(userFieldRelation);
        if(count > 0){
            result.setSuccessMsg("保存成功!");
        }else {
            result.setSuccessMsg("保存失败！");
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
