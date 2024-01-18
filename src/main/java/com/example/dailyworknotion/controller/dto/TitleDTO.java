package com.example.dailyworknotion.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class TitleDTO {
    private String id;
    private String type;
    private List<TextDTO> title;
}
