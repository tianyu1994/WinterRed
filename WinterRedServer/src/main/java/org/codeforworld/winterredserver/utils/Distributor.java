package org.codeforworld.winterredserver.utils;

import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.ProfessionalField;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.mapper.CheckManMapper;
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

    /**
     * 返回谣言对应的专家
     * @param rumorInfo 谣言
     * @return 专家 or null
     * @throws IOException
     */
    public static CheckMan checkRumorInfo(RumorInfo rumorInfo) throws IOException {
        String topic = Classifier.recognizeTopicOf(rumorInfo.getTitle());
        ProfessionalField field = new ProfessionalField();
        field.setFieldName("topic");
        List<ProfessionalField> resultList = professionalFieldMapper.queryProfessionalField(field);
        String topicId = resultList.get(0).getId().toString();
        //发生地暂时写死
        String rumorInfoPlace = "中国";
        List<Map<String, Object>> rumorInfoList = new ArrayList<>();
        List<CheckMan> checkManList = new ArrayList<>();
        checkManList = checkManMapper.queryCheckMen(rumorInfoPlace);
        //本平台数据库没有中国的专家
        if (checkManList.size() == 0) {
            return null;
        }

        for (CheckMan checkMan : checkManList) {
            if (checkMan.getBelongArea().equals(topic)) {
                return checkMan;
            }
        }
        return null;
    }
}
