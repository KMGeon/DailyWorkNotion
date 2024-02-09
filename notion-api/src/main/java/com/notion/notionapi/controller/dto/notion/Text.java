package com.notion.notionapi.controller.dto.notion;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class Text {
    private String content;
    private Object link;
}
