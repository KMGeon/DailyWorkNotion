package com.example.dailyworknotion.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageListResponseDTO {
    private String object;
    private List<PageDTO> results;
    private String next_cursor;
    private boolean has_more;
    private String type;
    private Object page_or_database;
    private String request_id;
}


