package com.example.dailyworknotion.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PageDTO {
    private String object;
    private String id;
    private String created_time;
    private String last_edited_time;
    private UserDTO created_by;
    private UserDTO last_edited_by;
    private Object cover;
    private Object icon;
    private ParentDTO parent;
    private boolean archived;
    private PropertiesDTO properties;
    private String url;
    private String public_url;

}