package com.example.dailyworknotion.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class PropertiesDTO {
    private DateDTO End;
    private TitleDTO Task_name;
    private PeopleDTO assign_property; // Fix: Correct field name
    private StatusDTO Status;
    private DateDTO Start;
}
