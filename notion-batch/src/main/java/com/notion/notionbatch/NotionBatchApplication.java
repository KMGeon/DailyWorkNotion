package com.notion.notionbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableBatchProcessing
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.notion.notionbatch" , "com.notion.notioncore"})
@EnableJpaRepositories(basePackages =  "com.notion.notioncore.repository")
@EntityScan(basePackages = "com.notion.notioncore.domain")
public class NotionBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotionBatchApplication.class, args);
    }

}
