package com.example.dailyworknotion.controller.dto.notion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class Title {
    private Text text;
    private Annotations annotations;
    private String plain_text;
    private String href;
}
