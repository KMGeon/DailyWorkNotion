package com.example.dailyworknotion.controller.dto.notion;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class NotionApiResponse {
    private String object;
    private List<Page> results;
    private String next_cursor;
    private boolean has_more;
    private String request_id;
}
