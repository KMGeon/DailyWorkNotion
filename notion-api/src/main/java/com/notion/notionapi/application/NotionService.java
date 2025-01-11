package com.notion.notionapi.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notion.notionapi.controller.dto.notion.*;
import com.notion.notioncore.domain.Notion;
import com.notion.notioncore.repository.NotionRepository;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


@Service
public class NotionService {

    @Value("${notion.secret}")
    private String NOTION_SECRET;

    @Value("${notion.version}")
    private String NOTION_VERSION_VALUE;

    @Value("${notion.dbId}")
    private String NOTION_DB_ID;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final NotionRepository notionRepository;


    public NotionService(
            RestTemplate restTemplate,
            ObjectMapper objectMapper,
            NotionRepository notionRepository
    ) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.notionRepository = notionRepository;
    }

    public NotionApiResponse getNotionDatabase() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(NOTION_SECRET);
        headers.add("Notion-Version", NOTION_VERSION_VALUE);

        logger.info("====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_SECRET : {}", NOTION_SECRET);
        logger.info("====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_VERSION_VALUE : {}", NOTION_VERSION_VALUE);

        URI uri = UriComponentsBuilder
                .fromUriString("https://api.notion.com/v1/databases/{databaseId}/query")
                .encode()
                .buildAndExpand(NOTION_DB_ID)
                .toUri();

        logger.info("====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] NOTION_DB_ID : {}", NOTION_DB_ID);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestEntity, String.class);

        try {
            return objectMapper.readValue(response.getBody(), NotionApiResponse.class);
        } catch (JsonProcessingException e) {
            logger.warn("{} /getNotionDatabase throw JsonProcessingException : {}, Stack Trace : {}", getClass().getSimpleName(), e.getMessage(), Arrays.toString(e.getStackTrace()));
            throw new RuntimeException("Error processing Notion API response", e);
        }
    }

    /**
     * todo : retry로 네트워크 exception 처리
     */
    @Transactional
    public void saveNotionTask() {
        logger.info("====== /api/notion [{}.saveNotionTask()] Start ====== ", getClass().getSimpleName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        NotionApiResponse notionTaskResponse = getNotionDatabase();

        String notionRequestId = notionTaskResponse.getRequest_id();

        logger.info("====== /api/notion [{}.saveNotionTask()] NotionRequestId : {}", getClass().getSimpleName(), notionRequestId);

        notionTaskResponse.getResults()
                .forEach(page -> {
                    LocalDateTime createdTime = LocalDateTime.parse(page.getCreated_time(), formatter);
                    LocalDateTime editedTime = LocalDateTime.parse(page.getLast_edited_time(), formatter);

                    Properties properties = page.getProperties();

                    // 우선순위
                    String priorityName = properties.getPriority().getSelect().getName();
                    // 분류
                    String categoryName = properties.getCategory().getSelect().getName();
                    // 상태
                    String statusName = properties.getStatus().getSelect().getName();
                    // 요청날짜 시작
                    String requestStart = properties.getRequestDate().getPropertiesDates().getStart();
                    // 기한 시작
                    String deadLine = properties.getDeadline().getPropertiesDates().getStart();
                    // 처리
                    List<People> processingPeople = properties.getProcessing().getPeople();
                    // 요청
                    List<People> requestPeople = properties.getRequest().getPeople();
                    // 노션 업무 타이틀
                    List<Text> notionTaskTitle = properties.getName().getTitle().stream()
                            .map(Title::getText)
                            .toList();

                    logger.info("====== /api/notion [{}.saveNotionTask()] priorityName : {}", getClass().getSimpleName(), priorityName);
                    logger.info("====== /api/notion [{}.saveNotionTask()] categoryName : {}", getClass().getSimpleName(), categoryName);
                    logger.info("====== /api/notion [{}.saveNotionTask()] statusName : {}", getClass().getSimpleName(), statusName);
                    logger.info("====== /api/notion [{}.saveNotionTask()] requestStart : {}", getClass().getSimpleName(), requestStart);
                    logger.info("====== /api/notion [{}.saveNotionTask()] deadLine : {}", getClass().getSimpleName(), deadLine);

                    logger.info("====== /api/notion [{}.saveNotionTask()] processingPeople : {}", getClass().getSimpleName(), processingPeople.toString());
                    logger.info("====== /api/notion [{}.saveNotionTask()] requestPeople : {}", getClass().getSimpleName(), requestPeople.toString());
                    logger.info("====== /api/notion [{}.saveNotionTask()] notionTaskTitle : {}", getClass().getSimpleName(), notionTaskTitle);

                    IntStream.range(0, notionTaskTitle.size()).forEach(i -> {
                        Notion notion = Notion.builder()
                                .request_id(notionRequestId)
                                .created_time(createdTime)
                                .last_edited_time(editedTime)
                                .priority(priorityName)
                                .category(categoryName)
                                .status(statusName)
                                .processing(processingPeople.isEmpty() ? null : processingPeople.get(i).getName())
                                .request(requestPeople.isEmpty() ? null : requestPeople.get(i).getName())
                                .requestDate(requestStart)
                                .deadLine(deadLine)
                                .title(notionTaskTitle.get(i).getContent())
                                .build();

                        notionRepository.save(notion);
                    });
                });
        logger.info("====== /api/notion [{}.saveNotionTask()] End ====== ", getClass().getSimpleName());
    }
}