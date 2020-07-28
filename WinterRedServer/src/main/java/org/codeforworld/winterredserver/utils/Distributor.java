package org.codeforworld.winterredserver.utils;

import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.CheckPlat;
import org.codeforworld.winterredserver.entity.ProfessionalField;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.mapper.CheckManMapper;
import org.codeforworld.winterredserver.mapper.CheckPlatMapper;
import org.codeforworld.winterredserver.mapper.ProfessionalFieldMapper;
import org.codeforworld.winterredserver.mapper.RumorInfoMapper;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Distributor {
    @Resource
    private static RumorInfoMapper rumorInfoMapper;
    @Resource
    private static CheckManMapper checkManMapper;
    @Resource
    private static ProfessionalFieldMapper professionalFieldMapper;
    @Resource
    private static CheckPlatMapper checkPlatMapper;
    /**
     * 返回谣言对应的专家
     * @param rumorInfo 谣言
     * @return 专家 or null
     * @throws IOException
     */
    public static CheckMan checkRumorInfo(RumorInfo rumorInfo) throws IOException {
        //这是从数据库传来的一条信息，一些数据信息应该已经初始化了，比如领域已保存了领域ID

        //发生地
        String rumorInfoPlace = Classifier.recognizeLocationOf(rumorInfo.getTitle());
        //根据发生地查找所有事发地平台
        List<CheckPlat> checkPlatList = new ArrayList<>();
        CheckPlat checkPlat = new CheckPlat();
        checkPlat.setBelongArea(rumorInfoPlace);
        checkPlatList = checkPlatMapper.queryCheckPlat(checkPlat);

        //本平台数据库没有事发地的平台信息
        CheckPlat toCheckPlat = new CheckPlat();
        if (checkPlatList.size() == 0) {
            return null;
        } else {
            toCheckPlat = checkPlatList.get(0);
        }

        //识别领域
        String topic = Classifier.recognizeTopicOf(rumorInfo.getTitle());

        //领域
        ProfessionalField field = new ProfessionalField();
        field.setFieldName(topic);

        List<ProfessionalField> resultList = professionalFieldMapper.queryProfessionalField(field);
        String topicId = resultList.get(0).getId().toString();

        List<Map<String, Object>> rumorInfoList = new ArrayList<>();


        List<CheckMan> checkManList = new ArrayList<>();
        for (CheckMan checkMan : checkManList) {
            if (checkMan.getBelongArea().equals(topic)) {
                return checkMan;
            }
        }
        return null;
    }
}
