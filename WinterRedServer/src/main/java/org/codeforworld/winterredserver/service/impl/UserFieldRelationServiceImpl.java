package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.entity.UserFieldRelation;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.SubscribeUserMapper;
import org.codeforworld.winterredserver.mapper.UserFieldRelationMapper;
import org.codeforworld.winterredserver.service.UserFieldRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订阅用户领域关系表 服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Service
public class UserFieldRelationServiceImpl extends ServiceImpl<UserFieldRelationMapper, UserFieldRelation> implements UserFieldRelationService {

    @Resource
    private UserFieldRelationMapper userFieldRelationMapper;
    @Resource
    private SubscribeUserMapper subscribeUserMapper;

    @Override
    public List<UserFieldRelation> queryUserFieldRelation(UserFieldRelation userFieldRelation) {
        return userFieldRelationMapper.queryUserFieldRelation(userFieldRelation);
    }

    @Override
    public Result saveOrUpdateUserFieldRelation(UserFieldRelation userFieldRelation) {
        Result result = new Result();
        boolean isExists = checkUnique(userFieldRelation);
        int i = 0;
        if(isExists){
            result.setErrorMsg("订阅用户id+专业领域id已经存在，不能重复新增订阅用户领域关系信息！");
            return result;
        }
        if(userFieldRelation.getId() != null){
            i = userFieldRelationMapper.updateById(userFieldRelation);
        }else {
            i = userFieldRelationMapper.insert(userFieldRelation);
        }
        if(i > 0){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @Override
    public List<SubscribeUser> queryByProfessionalFieldId(Integer professionalFieldId) {
        UserFieldRelation param = new UserFieldRelation();
        param.setProfessionalFieldId(professionalFieldId);
        List<UserFieldRelation> list = userFieldRelationMapper.queryUserFieldRelation(param);
        List<SubscribeUser> subscribeUserList = new ArrayList<>();
        for (UserFieldRelation userFieldRelation : list) {
            if(userFieldRelation.getUserId() != null){
                SubscribeUser subscribeUser = subscribeUserMapper.selectById(userFieldRelation.getUserId());
                subscribeUserList.add(subscribeUser);
            }
        }
        return subscribeUserList;
    }

    /**
     * 检查唯一索引
     * @param userFieldRelation
     * @return
     */
    private boolean checkUnique(UserFieldRelation userFieldRelation) {
        boolean isExists = false;
        QueryWrapper<UserFieldRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userFieldRelation.getUserId());
        queryWrapper.eq("professional_field_id", userFieldRelation.getProfessionalFieldId());
        List<UserFieldRelation> askUserList = userFieldRelationMapper.selectList(queryWrapper);
        if (askUserList == null || askUserList.size() < 1) {
            return isExists;
        } else if (userFieldRelation.getId() != null && askUserList.get(0).getId() == userFieldRelation.getId()) {
            return isExists;
        } else {
            isExists = true;
            return isExists;
        }
    }
}
