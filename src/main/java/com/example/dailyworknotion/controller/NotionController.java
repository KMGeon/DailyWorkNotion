package com.example.dailyworknotion.controller;

import com.example.dailyworknotion.application.NotionService;
import com.example.dailyworknotion.controller.dto.notion.NotionApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<NotionApiResponse> getNotionDatabase(HttpServletRequest request) {
        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] start ======");
        try {
            NotionApiResponse responseDTO = notionService.getNotionDatabase(request);
            return ResponseEntity.ok(responseDTO);
        } catch (JsonProcessingException e) {
            logger.error("DTO 매핑에 실패를 하였습니다", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] end ======");
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Void> saveNotion(HttpServletRequest request) {
        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/saveNotion [" + getClass().getSimpleName() + ".saveNotion()] start ======");
        try {
            NotionApiResponse responseDTO = notionService.getNotionDatabase(request);

        } catch (JsonProcessingException e) {
            logger.error("DTO 매핑에 실패를 하였습니다", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            logger.info("[" + request.getRemoteAddr() + "] ====== /notion/saveNotion [" + getClass().getSimpleName() + ".saveNotion()] end ======");
        }
        return null;
    }
}
