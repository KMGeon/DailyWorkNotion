package com.example.dailyworknotion.controller.dto.notion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesDate {
    private String start;
    private String end;
    private String time_zone;
}
