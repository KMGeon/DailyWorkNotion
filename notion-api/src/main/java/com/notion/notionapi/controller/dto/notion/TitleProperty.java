package com.notion.notionapi.controller.dto.notion;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class TitleProperty {
    private String id;
    private String type;
    private List<Title> title;
}
