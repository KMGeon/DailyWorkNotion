package com.notion.notionapi.controller.dto.notion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class Title {
    private Text text;
    private Annotations annotations;
    private String plain_text;
    private String href;
}
