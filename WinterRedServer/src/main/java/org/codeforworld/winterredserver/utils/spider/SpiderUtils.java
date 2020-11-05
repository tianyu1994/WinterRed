package org.codeforworld.winterredserver.utils.spider;

import java.util.List;

/**
 * @author: kfzx-menghj
 * @Time: 2020/11/5  14:34
 */
public class SpiderUtils {
    /**
     * 拿到第4个key到倒数第3个key的value
     *
     * @param allValues
     * @return
     */
    public static String getContext(List<String> allValues) {
        String context = "";
        /**
         *         0 = "公安相关车辆出现在华熙LIVE·五棵松的视频被传到网上后，引发一些市民的猜测，甚至传闻称“华熙沦陷”。"
         *         1 = "谣言"
         *         2 = ""
         *         3 = "真是情况是，6月14日晚上，海淀公安分局针对近期北京新冠肺炎疫情出现反弹，在华熙LIVE·五棵松进行了集结演练。"
         *         4 = "“自新冠疫情发生以来，华熙LIVE·五棵松商区积极做好防疫工作，公共区域坚持每日进行五次以上消杀，做好室内外通风，全体商户自查、互查，保证进货货源安全卫生。”华熙LIVE·五棵松表示，目前商区和调调街正常营业，也提醒前来华熙LIVE·五棵松的市民朋友戴好口罩，做好自我防护。"
         *         5 = "北京日报"
         *         6 = ""
         */
        for (int i = 3; i < allValues.size() - 2; i++) {
            try {
                context = context + allValues.get(i) + "\n";
            } catch (Exception e) {
                //可能会越界
                return context;
            }
        }
        return context;
    }
}
