package com.example.dailyworknotion.controller;

import com.example.dailyworknotion.controller.dto.NotionApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping(value = "/notion")
public class NotionController {

    private final Logger logger = LoggerFactory.getLogger(NotionController.class);

    @Value("${notion.secret}")
    private String NOTION_SECRET;

    @Value("${notion.version}")
    private String NOTION_VERSION_VALUE;

    @Value("${notion.db.id}")
    private String NOTION_DB_ID;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final static String NOTION_VERSION_KEY = "Notion-Version";


    public NotionController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * todo
     * 1. Record로 변경하기
     * 2. 도메인 변경하기
     */

    @GetMapping("/database")
    public ResponseEntity<NotionApiResponse> getNotionDatabase(HttpServletRequest request) {
        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] start ======");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(NOTION_SECRET);
        headers.add(NOTION_VERSION_KEY, NOTION_VERSION_VALUE);

        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] notion_secret : {}" , NOTION_SECRET);
        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_VERSION_KEY : {}" , NOTION_VERSION_KEY);
        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_VERSION_VALUE : {}" , NOTION_VERSION_VALUE);

        URI uri = UriComponentsBuilder
                .fromUriString("https://api.notion.com/v1/databases/{databaseId}/query")
                .encode()
                .buildAndExpand(NOTION_DB_ID)
                .toUri();

        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_DB_ID : {}" , NOTION_DB_ID);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestEntity, String.class);

        try {
            NotionApiResponse responseDTO = objectMapper.readValue(response.getBody(), NotionApiResponse.class);
            return ResponseEntity.ok(responseDTO);
        } catch (JsonProcessingException e) {
            logger.error("DTO 매핑에 실패를 하였습니다", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }finally {
            logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] end ======");
        }
    }
}
