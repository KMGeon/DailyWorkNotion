package com.example.dailyworknotion.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class DateInfoDTO {
    private String start;
    private String end;
    private String time_zone;
}
