package org.codeforworld.winterredserver.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.CheckPlat;
import org.codeforworld.winterredserver.entity.ProfessionalField;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.enumType.CheckStatus;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.CheckManMapper;
import org.codeforworld.winterredserver.mapper.CheckPlatMapper;
import org.codeforworld.winterredserver.mapper.ProfessionalFieldMapper;
import org.codeforworld.winterredserver.mapper.RumorInfoMapper;
import org.codeforworld.winterredserver.service.RumorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.codeforworld.winterredserver.utils.BlockchainUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Resource
    private CheckPlatMapper checkPlatMapper;
    private static final String SEPARATE = "#===这是分隔符===#";

    @Override
    public List<RumorInfo> queryRumorInfo(RumorInfo rumorInfo) {
        List<RumorInfo> list = rumorInfoMapper.queryRumorInfo(rumorInfo);
        for (RumorInfo info : list) {
            setRumorOtherInfo(info);
        }
        return list;
    }

    /**
     * 封装谣言信息关联表的信息
     * @param rumorInfo
     */
    private void setRumorOtherInfo(RumorInfo rumorInfo){
        if(rumorInfo.getProfessionalFieldId() != null){
            ProfessionalField param = new ProfessionalField();
            param.setId(rumorInfo.getProfessionalFieldId());
            List<String> fieldList = professionalFieldMapper.getAllFieldName(param);
            rumorInfo.setProfessionalFieldName(fieldList.get(0));
        }
        if(rumorInfo.getCheckManId() != null){
            CheckMan checkMan = checkManMapper.selectById(rumorInfo.getCheckManId());
            if(checkMan != null){
                rumorInfo.setCheckManName(checkMan.getCheckmanName());
                CheckPlat checkPlat = checkPlatMapper.selectById(checkMan.getOrganizationId());
                if(checkPlat != null){
                    rumorInfo.setOrganizationName(checkPlat.getOrganizationName());
                }
            }

        }
        String checkPoint = rumorInfo.getCheckPoint();
        if(StringUtils.isNotEmpty(checkPoint)){
            String[] checkPointArr = checkPoint.split(SEPARATE);
            List<String> checkPoints = Arrays.asList(checkPointArr);
            rumorInfo.setCheckPoints(checkPoints);
        }
    }

    @Override
    public Integer insert(RumorInfo rumorInfo) {
        /**
         * 入链
         */
        BlockchainUtil.pushRumorInfo2Chain(rumorInfo);
        return rumorInfoMapper.insert(rumorInfo);
    }

    @Override
    public Result saveOrUpdateRumorInfo(RumorInfo rumorInfo) {
        Result result = new Result();
        int count = 0;
        if(rumorInfo.getId() == null){
            rumorInfo.setStatus(CheckStatus.WAIT_CHECK.getName());
            rumorInfo.setCreateOn(LocalDateTime.now());
            rumorInfo.setUpdateOn(LocalDateTime.now());
            count = rumorInfoMapper.insert(rumorInfo);
        }else{
            rumorInfo.setUpdateOn(LocalDateTime.now());
            count = rumorInfoMapper.updateById(rumorInfo);
        }
        if(count > 0){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @Override
    public Result saveOrUpdateRumorInfoCheck(RumorInfo rumorInfo) {
        Result result = new Result();
        int count = 0;
        List<String> checkPointList = rumorInfo.getCheckPoints();
        StringBuffer checkPoint = new StringBuffer();
        if(checkPointList != null && checkPointList.size() > 0){
            for (int i = 0; i < checkPointList.size(); i++) {
                checkPoint.append(checkPointList.get(i)).append(SEPARATE);
            }
            rumorInfo.setCheckPoint(checkPoint.toString());
        }
        if(rumorInfo.getId() == null){
            rumorInfo.setStatus(CheckStatus.WAIT_CHECK.getName());
            rumorInfo.setCreateOn(LocalDateTime.now());
            rumorInfo.setUpdateOn(LocalDateTime.now());
            count = rumorInfoMapper.insert(rumorInfo);
        }else{
            rumorInfo.setUpdateOn(LocalDateTime.now());
            count = rumorInfoMapper.updateById(rumorInfo);
        }
        if(count > 0){
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
    public RumorInfo queryById(Integer id) {
        RumorInfo info = rumorInfoMapper.queryById(id);
        setRumorOtherInfo(info);
        return info;
    }

    @Override
    public RumorInfo queryRumorInfoById(String id) {
        return rumorInfoMapper.queryRumorInfoById(id);
    }

    @Override
    public RumorInfo queryRolledRumorInfoById(Integer id){
        RumorInfo rumorInfo = rumorInfoMapper.queryRolledRumorInfoById(id);
        if (rumorInfo == null){
            return rumorInfoMapper.queryRolledRumorInfoById(Integer.valueOf(1));
        }
        return rumorInfo;
    }
}
