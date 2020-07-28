package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codeforworld.winterredserver.entity.CheckPlat;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.CheckPlatMapper;
import org.codeforworld.winterredserver.service.CheckPlatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 核查平台表 服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Service
public class CheckPlatServiceImpl extends ServiceImpl<CheckPlatMapper, CheckPlat> implements CheckPlatService {

    @Resource
    private CheckPlatMapper checkPlatMapper;

    @Override
    public List<CheckPlat> queryCheckPlat(CheckPlat checkPlat) {
        return checkPlatMapper.queryCheckPlat(checkPlat);
    }

    @Override
    public Result saveOrUpdateCheckPlat(CheckPlat checkPlat) {
        Result result = new Result();
        boolean isExists = checkUnique(checkPlat);
        int i = 0;
        if(isExists){
            result.setErrorMsg("机构名称已经存在，不能重复新增机构信息！");
            return result;
        }
        if(checkPlat.getId() != null){
            i = checkPlatMapper.updateById(checkPlat);
        }else {
            i = checkPlatMapper.insert(checkPlat);
        }
        if(i > 0){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @Override
    public List<String> getAllOrganizationName(CheckPlat checkPlat) {
        List<String> list = checkPlatMapper.getAllOrganizationName(checkPlat);
        return list;
    }

    @Override
    public List<String> getAllBelongArea(CheckPlat checkPlat) {
        List<String> list = checkPlatMapper.getAllBelongArea(checkPlat);
        return list;
    }

    /**
     * 检查唯一索引
     * @param checkPlat
     * @return
     */
    private boolean checkUnique(CheckPlat checkPlat){
        boolean isExists = false;
        QueryWrapper<CheckPlat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("organization_name", checkPlat.getOrganizationName());
        List<CheckPlat> askUserList = checkPlatMapper.selectList(queryWrapper);
        if(askUserList == null || askUserList.size() < 1){
            return isExists;
        }else if(checkPlat.getId() != null && askUserList.get(0).getId() == checkPlat.getId()){
            return isExists;
        }else {
            isExists = true;
            return isExists;
        }
    }


}
