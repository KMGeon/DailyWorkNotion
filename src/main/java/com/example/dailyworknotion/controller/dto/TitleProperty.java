package com.example.dailyworknotion.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class TitleProperty {
    private String id;
    private String type;
    private List<Title> title;
}
