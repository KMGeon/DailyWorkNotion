package com.notion.notionapi.controller.dto.notion;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class Page {
    private String object;
    private String id;
    private String created_time;
    private String last_edited_time;
    private User createdBy;
    private User last_edited_by;
    private String cover;
    private String icon;
    private boolean archived;
    private Properties properties;
    private String url;
}
