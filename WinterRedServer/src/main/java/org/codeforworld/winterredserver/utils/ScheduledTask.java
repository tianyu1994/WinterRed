package org.codeforworld.winterredserver.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codeforworld.winterredserver.entity.*;
import org.codeforworld.winterredserver.mapper.AskUserMapper;
import org.codeforworld.winterredserver.mapper.RumorInfoMapper;
import org.codeforworld.winterredserver.mapper.SubscribeUserMapper;
import org.codeforworld.winterredserver.mapper.UserFieldRelationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: kfzx-menghj
 * @Time: 2020/7/28  14:18
 */
@Service
public class ScheduledTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    @Resource
    RumorInfoMapper rumorInfoMapper;
    @Resource
    private AskUserMapper askUserMapper;
    @Resource
    private UserFieldRelationMapper userFieldRelationMapper;
    @Resource
    private SubscribeUserMapper subscribeUserMapper;

    /**
     * 每天中午十二点触发定时分发任务
     */
//    @Scheduled(cron = "*/5 * * * * ?") //这里为每5S触发一次
    @Scheduled(cron = "0 0 12 * * ?")
    public void distributeTask() {
        logger.info("分发任务启动");
        //拿到昨天日期
        LocalDateTime now = LocalDateTime.now();
        now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime yesterday = now.minus(2, ChronoUnit.DAYS);

        sendToCheckMan(yesterday); //待核查信息发送给核查人员
        sendToQuestioner(yesterday); //已核查信息发送给提问人员
        sendToSubscriber(yesterday); //发送已核查信息给订阅用户

    }


    /**
     * 待核查信息发送给核查人员
     *
     * @param yesterday
     */
    private void sendToCheckMan(LocalDateTime yesterday) {
        //拿到最近24小时待核查谣言
        QueryWrapper<RumorInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("update_on", yesterday).eq("status", "存疑");
        List<RumorInfo> rumorInfoList = rumorInfoMapper.selectList(queryWrapper);

        //遍历调用接口,给每个checkMan发邮件
        for (RumorInfo rumorInfo : rumorInfoList) {
            //TODO checkRumorInfo还未实现
            CheckMan checkMan = checkRumorInfo(rumorInfo);
            MailUtil.sendMail(checkMan.getEmail());
        }
    }


    /**
     * 已核查信息发送给提问人员
     *
     * @param yesterday
     */
    private void sendToQuestioner(LocalDateTime yesterday) {
        //拿到最近24小时已核查谣言
        QueryWrapper<RumorInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("update_on", yesterday).ne("status", "存疑");
        List<RumorInfo> rumorInfoList = rumorInfoMapper.selectList(queryWrapper);

        for (RumorInfo rumorInfo : rumorInfoList) {
            //用ID查到askUser
            AskUser askUser = new AskUser();
            askUser.setId(rumorInfo.getAskUserId());
            List<AskUser> askUserList = askUserMapper.queryAskUser(askUser);
            //用id应该只能查到一条，用该条的邮箱发邮件
            if (1 == askUserList.size()) {
                String email = askUserList.get(0).getEmail();
                MailUtil.sendMail(email);
            }
        }

    }

    /**
     * 发送已核查信息给订阅用户
     *
     * @param yesterday
     */
    private void sendToSubscriber(LocalDateTime yesterday) {
        //拿到最近24小时已核查谣言
        QueryWrapper<RumorInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("update_on", yesterday).ne("status", "存疑");
        List<RumorInfo> rumorInfoList = rumorInfoMapper.selectList(queryWrapper);

        for (RumorInfo rumorInfo : rumorInfoList) {
            Integer professionalFieldId = rumorInfo.getProfessionalFieldId();
            //根据领域ID拿到用户id
            UserFieldRelation userFieldRelation = new UserFieldRelation();
            userFieldRelation.setProfessionalFieldId(professionalFieldId);
            List<UserFieldRelation> userFieldRelations = userFieldRelationMapper.queryUserFieldRelation(userFieldRelation);
            for (UserFieldRelation userFieldRelation1 : userFieldRelations) {
                //遍历拿到某领域内的第i位专家，用userId查询user得到email
                Integer userId = userFieldRelation1.getUserId();

                SubscribeUser subscribeUser = new SubscribeUser();
                subscribeUser.setId(userId);
                List<SubscribeUser> subscribeUsers = subscribeUserMapper.querySubscribeUser(subscribeUser);

                if (1 == subscribeUsers.size()) {
                    String email = subscribeUsers.get(0).getEmail();
                    MailUtil.sendMail(email);
                }
            }
        }
    }

    /**
     * mock方法 迟早要删
     *
     * @param rumorInfo
     * @return
     */
    @Deprecated
    private CheckMan checkRumorInfo(RumorInfo rumorInfo) {
        return new CheckMan();
    }
}
