package org.codeforworld.winterredserver.utils;

import org.codeforworld.winterredserver.entity.ProfessionalField;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.service.ProfessionalFieldService;
import org.codeforworld.winterredserver.service.RumorInfoService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RumorInfoUtil {
    @Resource
    private static ProfessionalFieldService professionalFieldService;
    @Resource
    private static RumorInfoService rumorInfoService;
    /**
     * 判断给定信息在数据库中是否存在类似信息
     * @param msg
     * @return
     */
    public static boolean existSimilarRumorInfo(String msg) throws IOException {
        List<ProfessionalField> fieldList = getProfessionalFields(msg);
        //不存在该话题，则不存在相似信息
        if (fieldList == null || fieldList.size() == 0) {
            return false;
        }
        Integer professionId = fieldList.get(0).getId();

        List<RumorInfo> rumorInfos = getRumorInfosOfTheProfession(professionId);
        for (RumorInfo rumorInfo : rumorInfos) {
            boolean isSimilar = Comparator.compareTwoTextSimilarity(rumorInfo.getTitle(), msg, true);
            if (isSimilar) {
                return true;
            }
        }
        return false;
    }

    private static List<RumorInfo> getRumorInfosOfTheProfession(Integer professionId) {
        List<RumorInfo> rumorInfos = new ArrayList<>();
        Map<String, Object> fieldIdMap = new HashMap<>();
        fieldIdMap.put("professional_field_id", professionId);
        rumorInfos = rumorInfoService.listByMap(fieldIdMap);
        return rumorInfos;
    }

    private static List<ProfessionalField> getProfessionalFields(String msg) throws IOException {
        String topic = Classifier.recognizeTopicOf(msg);
        Map<String, Object> fieldNameMap = new HashMap<>();
        fieldNameMap.put("field_name", topic);
        return professionalFieldService.listByMap(fieldNameMap);
    }

    public static List<RumorInfo> recommendSimilarRumorInfo(String msg) throws IOException {
        Integer professionId = getProfessionalFields(msg).get(0).getId();
        return getRumorInfosOfTheProfession(professionId);
    }
}
