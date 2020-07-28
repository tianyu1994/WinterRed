package org.codeforworld.winterredserver.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.ProfessionalField;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.enumType.CheckStatus;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.CheckManMapper;
import org.codeforworld.winterredserver.mapper.ProfessionalFieldMapper;
import org.codeforworld.winterredserver.mapper.RumorInfoMapper;
import org.codeforworld.winterredserver.service.RumorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 谣言信息表 服务实现类
 * </p>
 *
 * @author kfzx-menghj
 * @since 2020-07-25
 */
@Service
public class RumorInfoServiceImpl extends ServiceImpl<RumorInfoMapper, RumorInfo> implements RumorInfoService {
    @Resource
    private RumorInfoMapper rumorInfoMapper;
    @Resource
    private ProfessionalFieldMapper professionalFieldMapper;
    @Resource
    private CheckManMapper checkManMapper;

    @Override
    public List<RumorInfo> queryRumorInfo(RumorInfo rumorInfo) {
        List<RumorInfo> list = rumorInfoMapper.queryRumorInfo(rumorInfo);
        for (RumorInfo info : list) {
            if(info.getProfessionalFieldId() != null){
                ProfessionalField param = new ProfessionalField();
                param.setId(info.getProfessionalFieldId());
                List<String> fieldList = professionalFieldMapper.getAllFieldName(param);
                info.setProfessionalFieldName(fieldList.get(0));
            }
            if(info.getCheckManId() != null){
                CheckMan param = new CheckMan();
                param.setId(info.getCheckManId());
                List<CheckMan> checkManList = checkManMapper.queryCheckMan(param);
                if(checkManList != null && checkManList.size() > 0){
                    info.setCheckManName(checkManList.get(0).getCheckmanName());
                }
            }
        }
        return list;
    }

    @Override
    public Integer insert(RumorInfo rumorInfo) {
        return rumorInfoMapper.insert(rumorInfo);
    }

    @Override
    public Result saveOrUpdateRumorInfo(RumorInfo rumorInfo) {
        Result result = new Result();
        int i = 0;
        if(rumorInfo.getId() == null){
            rumorInfo.setStatus(CheckStatus.WAIT_CHECK.getName());
            rumorInfo.setCreateOn(LocalDateTime.now());
            rumorInfo.setUpdateOn(LocalDateTime.now());
            i = rumorInfoMapper.insert(rumorInfo);
        }
        rumorInfo.setUpdateOn(LocalDateTime.now());
        i = rumorInfoMapper.updateById(rumorInfo);
        if(i > 0){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @Override
    public Result modifyStatus(RumorInfo param){
        Result result = new Result();
        RumorInfo rumorInfo = rumorInfoMapper.selectById(param.getId());
        rumorInfo.setStatus(param.getStatus());
        rumorInfoMapper.updateById(rumorInfo);
        return result;
    }

    @Override
    public RumorInfo queryRumorInfoById(String id) {
        return rumorInfoMapper.queryRumorInfoById(id);
    }
}
