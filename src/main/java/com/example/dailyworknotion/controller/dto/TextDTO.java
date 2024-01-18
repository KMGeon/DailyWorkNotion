package com.example.dailyworknotion.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class TextDTO {
    private String type;
    private TextContentDTO text;
}
