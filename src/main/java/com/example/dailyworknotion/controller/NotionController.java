package com.example.dailyworknotion.controller;

import com.example.dailyworknotion.controller.dto.PageListResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @GetMapping("/database")
    public ResponseEntity<PageListResponseDTO> test() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(NOTION_SECRET);
        headers.add(NOTION_VERSION_KEY, NOTION_VERSION_VALUE);

        URI uri = UriComponentsBuilder
                .fromUriString("https://api.notion.com/v1/databases/{databaseId}/query")
                .encode()
                .buildAndExpand(NOTION_DB_ID)
                .toUri();


        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, requestEntity, String.class);

        try {
            PageListResponseDTO pageListResponseDTO = objectMapper.readValue(result.getBody(), PageListResponseDTO.class);
            return ResponseEntity.ok(pageListResponseDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
