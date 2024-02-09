package com.notion.notionapi.controller;

import com.notion.notionapi.ApiResponse;
import com.notion.notionapi.application.NotionService;
import com.notion.notionapi.controller.dto.notion.NotionApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class NotionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final NotionService notionService;

    public NotionController(NotionService notionService) {
        this.notionService = notionService;
    }

    @GetMapping("/notion")
    public ApiResponse<NotionApiResponse> getNotionDatabase() {
        logger.info("====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] start ======");
        try {
            NotionApiResponse responseDTO = notionService.getNotionDatabase();
            return ApiResponse.success(HttpStatus.OK, responseDTO);
        } finally {
            logger.info("====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] end ======");
        }
    }

    @PostMapping(value = "/notion")
    public ApiResponse<Void> saveNotion() {
        logger.info("====== /notion/saveNotion [" + getClass().getSimpleName() + ".saveNotion()] start ======");
        notionService.saveNotionTask();
        return ApiResponse.success(HttpStatus.CREATED, null);
    }
}
