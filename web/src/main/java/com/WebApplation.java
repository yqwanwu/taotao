package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 *
 *
 * 只有 主项目才使用 这个下面的那个插件配置， 多模块，
 * 启动项目要用 命令： spring-boot:run
 <build>
 <!-- 为jar包取名 -->
 <finalName>web</finalName>
 <plugins>
 <plugin>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-maven-plugin</artifactId>
 </plugin>
 </plugins>
 </build>
 *
 *
 *
 *
 */





@SpringBootApplication
public class WebApplation extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplation.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplation.class, args);
    }
}
