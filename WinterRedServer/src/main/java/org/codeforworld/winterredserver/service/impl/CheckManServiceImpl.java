package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.CheckManMapper;
import org.codeforworld.winterredserver.service.CheckManService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 核查人员表 服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Service
public class CheckManServiceImpl extends ServiceImpl<CheckManMapper, CheckMan> implements CheckManService {

    @Resource
    private CheckManMapper checkManMapper;

    @Override
    public List<CheckMan> queryCheckMan(CheckMan checkMan) {
        List<CheckMan> list = checkManMapper.queryCheckMan(checkMan);
        return list;
    }

    @Override
    public Result saveOrUpdateCheckMan(CheckMan checkMan) {
        Result result = new Result();
        boolean isExists = checkUnique(checkMan);
        int i = 0;
        if(isExists){
            result.setErrorMsg("机构id+邮箱已经存在，不能重复新增核查人员！");
            return result;
        }
        if(checkMan.getId() != null){
            i = checkManMapper.updateById(checkMan);
        }else {
            i = checkManMapper.insert(checkMan);
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
     * @param checkMan
     * @return
     */
    private boolean checkUnique(CheckMan checkMan){
        boolean isExists = false;
        QueryWrapper<CheckMan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("organization_id", checkMan.getOrganizationId());
        queryWrapper.eq("email", checkMan.getEmail());
        List<CheckMan> askUserList = checkManMapper.selectList(queryWrapper);
        if(askUserList == null || askUserList.size() < 1){
            return isExists;
        }else if(checkMan.getId() != null && askUserList.get(0).getId() == checkMan.getId()){
            return isExists;
        }else {
            isExists = true;
            return isExists;
        }
    }
    
}
