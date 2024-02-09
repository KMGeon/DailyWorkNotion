package com.notion.notionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.notion.notionapi" ,
                "com.notion.notioncore"
        }
)
public class NotionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotionApiApplication.class, args);
    }

}
