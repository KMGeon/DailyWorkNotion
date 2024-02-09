package com.example.dailyworknotion.controller.dto.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDate {
    private String id;
    private String type;

    @JsonProperty("people")
    private List<PropertiesDate> people;

}