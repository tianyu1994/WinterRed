package org.codeforworld.winterredserver.utils;

import org.codeforworld.winterredserver.entity.*;
import org.codeforworld.winterredserver.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Distributor {
    public static Distributor distributor;
    @PostConstruct
    public void init() {
        distributor = this;
    }
    @Resource
    private RumorInfoMapper rumorInfoMapper;
    @Resource
    private CheckManMapper checkManMapper;
    @Resource
    private ProfessionalFieldMapper professionalFieldMapper;
    @Resource
    private CheckPlatMapper checkPlatMapper;
    @Resource
    private CheckmanFieldRelationMapper checkmanFieldRelationMapper;

    /**
     * 返回谣言对应的专家
     * @param rumorInfo 谣言
     * @return 专家 or null
     * @throws IOException
     */
    public List<CheckMan> checkRumorInfo(RumorInfo rumorInfo) throws IOException {
        //这是从数据库传来的一条信息，一些数据信息应该已经初始化了，比如领域已保存了领域ID

        //发生地
        String rumorInfoPlace = Classifier.recognizeLocationOf(rumorInfo.getTitle());
        //根据发生地查找所有事发地专家
        List<CheckMan> checkMenOfNation = new ArrayList<>();
//        CheckMan checkMan2 = new CheckMan();
//        checkMan2.setBelongArea(rumorInfoPlace);
        Map<String, Object> nation = new HashMap<>();
        nation.put("belong_area", rumorInfoPlace);
        checkMenOfNation = distributor.checkManMapper.selectByMap(nation);

        Integer rumorInfoProfessionId = rumorInfo.getProfessionalFieldId();
        //本平台数据库事发地的专家信息
        if (!rumorInfoPlace.equals("") && checkMenOfNation.size() == 0) {
            return null;
        }
        List<CheckMan> checkMenOfNationAndProfession = new ArrayList<>();
        for (CheckMan checkMan : checkMenOfNation) {
            Map<String, Object> checkManId = new HashMap<>();
            checkManId.put("check_man_id", checkMan.getId());
            List<CheckmanFieldRelation> checkmanFieldRelations = distributor.checkmanFieldRelationMapper.selectByMap(checkManId);
            for (CheckmanFieldRelation relation : checkmanFieldRelations) {
                if (relation.getProfessionalFieldId().equals(rumorInfoProfessionId)) {
                    checkMenOfNationAndProfession.add(checkMan);
                }
            }
        }
        if (checkMenOfNationAndProfession.size() != 0) {
            return checkMenOfNationAndProfession;
        }

        //无事发地专家or事发地专家无该领域专家，则返回该领域内的专家们
        Map<String, Object> professionFiledId = new HashMap<>();
        professionFiledId.put("professional_field_id", rumorInfoProfessionId);
        List<CheckmanFieldRelation> checkmanFieldRelations = distributor.checkmanFieldRelationMapper.selectByMap(professionFiledId);

        List<CheckMan> checkMenOfProfession = new ArrayList<>();
        for (CheckmanFieldRelation relation : checkmanFieldRelations) {
            CheckMan checkManId = new CheckMan();
            checkManId.setId(relation.getCheckManId());
            CheckMan checkMan = distributor.checkManMapper.queryCheckMan(checkManId).get(0);
            checkMenOfProfession.add(checkMan);
        }
        if (checkMenOfProfession.size() != 0) {
            return checkMenOfProfession;
        }
        return null;
    }


}
