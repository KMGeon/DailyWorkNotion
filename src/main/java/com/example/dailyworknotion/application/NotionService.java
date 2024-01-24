package com.example.dailyworknotion.application;

import com.example.dailyworknotion.controller.dto.notion.NotionApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;


@Service
public class NotionService {

    @Value("${notion.secret}")
    private String NOTION_SECRET;

    @Value("${notion.version}")
    private String NOTION_VERSION_VALUE;

    @Value("${notion.db.id}")
    private String NOTION_DB_ID;

    private final Logger logger = LoggerFactory.getLogger(NotionService.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public NotionService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public NotionApiResponse getNotionDatabase(HttpServletRequest request) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(NOTION_SECRET);
        headers.add("Notion-Version", NOTION_VERSION_VALUE);

        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_SECRET : {}", NOTION_SECRET);
        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_VERSION_VALUE : {}", NOTION_VERSION_VALUE);

        URI uri = UriComponentsBuilder
                .fromUriString("https://api.notion.com/v1/databases/{databaseId}/query")
                .encode()
                .buildAndExpand(NOTION_DB_ID)
                .toUri();

        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_DB_ID : {}", NOTION_DB_ID);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestEntity, String.class);

        return objectMapper.readValue(response.getBody(), NotionApiResponse.class);
    }
}