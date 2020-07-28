package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.CheckmanFieldRelation;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.CheckManMapper;
import org.codeforworld.winterredserver.mapper.CheckmanFieldRelationMapper;
import org.codeforworld.winterredserver.service.CheckmanFieldRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 核查人员领域关系表 服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Service
public class CheckmanFieldRelationServiceImpl extends ServiceImpl<CheckmanFieldRelationMapper, CheckmanFieldRelation> implements CheckmanFieldRelationService {

    @Resource
    private CheckmanFieldRelationMapper checkmanFieldRelationMapper;

    @Resource
    private CheckManMapper CheckManMapper;

    @Override
    public List<CheckmanFieldRelation> queryCheckManFieldRelation(CheckmanFieldRelation checkmanFieldRelation) {
        List<CheckmanFieldRelation> list = checkmanFieldRelationMapper.queryCheckManFieldRelation(checkmanFieldRelation);
        return list;
    }


    @Override
    public Result saveOrUpdateCheckmanFieldRelation(CheckmanFieldRelation checkmanFieldRelation) {
        Result result = new Result();
        boolean isExists = checkUnique(checkmanFieldRelation);
        int i = 0;
        if(isExists){
            result.setErrorMsg("核查人员id+专业领域id已经存在，不能重复新增核查人员领域关系信息！");
            return result;
        }
        if(checkmanFieldRelation.getId() != null){
            i = checkmanFieldRelationMapper.updateById(checkmanFieldRelation);
        }else {
            i = checkmanFieldRelationMapper.insert(checkmanFieldRelation);
        }
        if(i > 0){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @Override
    public List<CheckMan> queryByProfessionalFieldId(Integer professionalFieldId) {
        CheckmanFieldRelation param = new CheckmanFieldRelation();
        param.setProfessionalFieldId(professionalFieldId);
        List<CheckmanFieldRelation> list = checkmanFieldRelationMapper.queryCheckManFieldRelation(param);
        List<CheckMan> checkManList = new ArrayList<>();
        for (CheckmanFieldRelation checkmanFieldRelation : list) {
            if(checkmanFieldRelation.getCheckManId() != null){
                CheckMan checkMan = CheckManMapper.selectById(checkmanFieldRelation.getCheckManId());
                checkManList.add(checkMan);
            }
        }
        return checkManList;
    }

    /**
     * 检查唯一索引
     * @param checkmanFieldRelation
     * @return
     */
    private boolean checkUnique(CheckmanFieldRelation checkmanFieldRelation) {
        boolean isExists = false;
        QueryWrapper<CheckmanFieldRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("check_man_id", checkmanFieldRelation.getCheckManId());
        queryWrapper.eq("professional_field_id", checkmanFieldRelation.getProfessionalFieldId());
        List<CheckmanFieldRelation> askUserList = checkmanFieldRelationMapper.selectList(queryWrapper);
        if (askUserList == null || askUserList.size() < 1) {
            return isExists;
        } else if (checkmanFieldRelation.getId() != null && askUserList.get(0).getId() == checkmanFieldRelation.getId()) {
            return isExists;
        } else {
            isExists = true;
            return isExists;
        }
    }
}
