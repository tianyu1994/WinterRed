package org.codeforworld.winterredserver.utils;

import org.codeforworld.winterredserver.entity.*;
import org.codeforworld.winterredserver.service.*;
import org.codeforworld.winterredserver.service.impl.CheckManServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Distributor {
    @Autowired
    private static RumorInfoService rumorInfoService;
    private static CheckManService checkManService = new CheckManServiceImpl();
    @Autowired
    private static ProfessionalFieldService professionalFieldService;
    @Autowired
    private static CheckPlatService checkPlatService;
    @Autowired
    private static CheckmanFieldRelationService checkmanFieldRelationService;

    /**
     * 返回谣言对应的专家
     * @param rumorInfo 谣言
     * @return 专家 or null
     * @throws IOException
     */
    public static List<CheckMan> checkRumorInfo(RumorInfo rumorInfo) throws IOException {
        //这是从数据库传来的一条信息，一些数据信息应该已经初始化了，比如领域已保存了领域ID

        //发生地
        String rumorInfoPlace = Classifier.recognizeLocationOf(rumorInfo.getTitle());
        //根据发生地查找所有事发地专家
        List<CheckMan> checkMenOfNation = new ArrayList<>();
        CheckMan checkMan2 = new CheckMan();
        checkMan2.setBelongArea(rumorInfoPlace);
        Map<String, Object> nation = new HashMap<>();
        nation.put("belong_area", rumorInfoPlace);
        checkMenOfNation = checkManService.queryCheckMan(checkMan2);

        Integer rumorInfoProfessionId = rumorInfo.getProfessionalFieldId();
        //本平台数据库事发地的平台信息
        if (checkMenOfNation.size() == 0) {
            return null;
        }
        for (CheckMan checkMan : checkMenOfNation) {
            List<CheckMan> checkMenOfNationAndProfession = new ArrayList<>();
            Map<String, Object> checkManId = new HashMap<>();
            checkManId.put("check_man_id", checkMan.getId());
            List<CheckmanFieldRelation> checkmanFieldRelations = checkmanFieldRelationService.listByMap(checkManId);
            for (CheckmanFieldRelation relation : checkmanFieldRelations) {
                if (relation.getProfessionalFieldId().equals(rumorInfoProfessionId)) {
                    checkMenOfNationAndProfession.add(checkMan);
                }
            }
            return checkMenOfNationAndProfession;
        }

        //无事发地专家，则返回该领域内的专家们
        Map<String, Object> professionFiledId = new HashMap<>();
        professionFiledId.put("professional_field_id", rumorInfoProfessionId);
        List<CheckmanFieldRelation> checkmanFieldRelations = checkmanFieldRelationService.listByMap(professionFiledId);

        List<CheckMan> checkMenOfProfession = new ArrayList<>();
        for (CheckmanFieldRelation relation : checkmanFieldRelations) {
            CheckMan checkManId = new CheckMan();
            checkManId.setId(relation.getCheckManId());
            CheckMan checkMan = checkManService.queryCheckMan(checkManId).get(0);
            checkMenOfProfession.add(checkMan);
        }
        return checkMenOfProfession;
    }


}
