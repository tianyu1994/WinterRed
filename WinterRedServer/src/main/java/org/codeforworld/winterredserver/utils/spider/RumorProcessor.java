package org.codeforworld.winterredserver.utils.spider;

import org.apache.commons.lang3.StringUtils;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.mapper.RumorInfoMapper;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author: kfzx-menghj
 * @Time: 2020/11/5  10:26
 */
@Component
public class RumorProcessor implements PageProcessor {
    @Resource
    RumorInfoMapper rumorInfoMapper;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private static RumorProcessor rumorProcessor;

    @PostConstruct
    public void init() {
        rumorProcessor = this;
        rumorProcessor.rumorInfoMapper = this.rumorInfoMapper;
    }

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://mbd\\.baidu\\.com/newspage/data/landingshare\\?[\\S]+)").all());
        page.putField("title", page.getHtml().xpath("//span[@class='titleTxt']/text()").toString());
        if (page.getResultItems().get("title") == null) {
            //skip this page
            page.setSkip(true);
            return;
        }
        List<String> allValues = page.getHtml().xpath("//span[@class='bjh-p']/text()").all();

        String title = page.getResultItems().get("title").toString();
        String rumor = allValues.get(0);
        String status = allValues.get(1);
        //2的内容是要点之后的换行符
        String content = SpiderUtils.getContext(allValues);
        String source = allValues.get(allValues.size() - 2);

        RumorInfo rumorInfo = new RumorInfo();
        LocalDateTime now = LocalDateTime.now();
        rumorInfo.setTitle(title);
        rumorInfo.setAbstractInfo(rumor);
        rumorInfo.setQuotedContent(content);
        rumorInfo.setCheckPoint(title);
        rumorInfo.setStatus(status);
        rumorInfo.setCreateOn(now);
        rumorInfo.setUpdateOn(now);
        rumorInfo.setCheckManId(1);
        rumorInfo.setProfessionalFieldId(12);
        rumorInfo.setAskUserId(1);
        rumorInfo.setSource(source);
        rumorInfo.setUpdateBy("系统");

        if (StringUtils.isNotEmpty(status)) {
            rumorProcessor.rumorInfoMapper.insert(rumorInfo);
        }


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void run() {
//    public static void main(String[] args) {
        Spider.create(new org.codeforworld.winterredserver.utils.spider.RumorProcessor())
                .addUrl("https://mbd.baidu.com/newspage/data/mdpage?tag=8&id=5807v")
                .thread(4)
                .run();
    }
}
