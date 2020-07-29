package org.codeforworld.winterredserver;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.utils.BlockchainUtil;
import org.codeforworld.winterredserver.utils.Classifier;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

/**
 * Spring Boot 应用启动类
 *
 * Created by bysocket on 16/4/26.
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("org.codeforworld.winterredserver.mapper")
@Slf4j
public class WinterRedServerStartUp {
    public static NaiveBayesModel model;
    public static WordVectorModel wordVectorModel;
    public static void main(String[] args) throws IOException {
        //加载分类模型
        //model = Classifier.trainOrLoadModel();
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(WinterRedServerStartUp.class,args);

    }
}
