package org.codeforworld.winterredserver.util;

import org.codeforworld.winterredserver.WinterRedServerStartUp;
import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.entity.CheckPlat;
import org.codeforworld.winterredserver.entity.RumorInfo;
import org.codeforworld.winterredserver.service.CheckManService;
import org.codeforworld.winterredserver.service.CheckPlatService;
import org.codeforworld.winterredserver.service.RumorInfoService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WinterRedServerStartUp.class)
public class handleDataTest {
    @Resource
    RumorInfoService rumorInfoService;
    @Resource
    CheckManService checkManService;
    @Resource
    CheckPlatService checkPlatService;


    @Test
    public void test_clean_rumorInfo_table(){
        RumorInfo rumorInfo = new RumorInfo();
        List<RumorInfo> rumorInfoList = rumorInfoService.queryRumorInfo(rumorInfo);

        for (RumorInfo rumorInfoTemp : rumorInfoList){
            if (rumorInfoTemp.getSource()==null || "".equals(rumorInfoTemp.getSource().trim())){
                System.out.println(rumorInfoTemp);
                rumorInfoService.removeById(rumorInfoTemp.getId());
            }
        }
    }



    @Test
    public void test_source_data_in_rumor_info_table_convert_into_check_plat_table(){
        RumorInfo rumorInfo = new RumorInfo();
        List<RumorInfo> rumorInfoList = rumorInfoService.queryRumorInfo(rumorInfo);

        List<String> sourceList = new ArrayList<>();
        for (RumorInfo rumorInfoTemp : rumorInfoList){
            if (sourceList.contains(rumorInfoTemp.getSource())){
                continue;
            }else {
                sourceList.add(rumorInfoTemp.getSource());
            }

        }
        for (String source : sourceList){
            CheckPlat checkPlat = new CheckPlat();
            checkPlat.setOrganizationName(source);
            checkPlat.setBelongArea("中国");
            checkPlat.setUpdateBy("系统扫描");
            checkPlat.setUpdateOn(LocalDateTime.now());

            checkPlatService.saveOrUpdate(checkPlat);
        }
    }

    @Test
    public void test_data_in_check_plat_table_convert_into_check_man_table(){
        CheckPlat checkPlat = new CheckPlat();
        List<CheckPlat> checkPlatList = checkPlatService.queryCheckPlat(checkPlat);

        for (CheckPlat checkPlatTemp : checkPlatList){
            CheckMan checkMan = new CheckMan();
            checkMan.setOrganizationId(checkPlatTemp.getId()+"");
            checkMan.setCheckmanName("");
            checkMan.setSex("男");
            checkMan.setEmail(randomGenerateEmailAddress());
            checkMan.setUserName(checkPlatTemp.getOrganizationName());
            checkMan.setBelongArea(checkPlatTemp.getBelongArea());
            checkMan.setUpdateOn(LocalDateTime.now());
            checkMan.setUpdateBy("系统扫描");

            checkManService.saveOrUpdate(checkMan);
        }
    }

        @Test
    public void test_update_in_check_plat_table_check_man_id_from_check_man_table(){
        RumorInfo rumorInfo = new RumorInfo();
        List<RumorInfo> rumorInfoList = rumorInfoService.queryRumorInfo(rumorInfo);

        CheckMan checkMan = new CheckMan();
        List<CheckMan> checkManList = checkManService.queryCheckMan(checkMan);

        for (RumorInfo rumorInfoTemp : rumorInfoList){
            for (CheckMan checkManTemp : checkManList) {
                if (rumorInfoTemp.getSource().equals(checkManTemp.getUserName())){
                    rumorInfoTemp.setCheckManId(checkManTemp.getId());
                }
            }
            rumorInfoService.saveOrUpdate(rumorInfoTemp);
        }
    }




    private String randomGenerateEmailAddress() {
        StringBuffer numStr = new StringBuffer("8080");
        for (int i = 4; i < 9; i++){
            numStr.append((int)(Math.random()*10));
        }
        return numStr.toString() + "@qq.com";
    }

}
