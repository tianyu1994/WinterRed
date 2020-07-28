package org.codeforworld.winterredserver;

import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.utils.BlockchainUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(WinterRedServerStartUp.class,args);

    }
}
