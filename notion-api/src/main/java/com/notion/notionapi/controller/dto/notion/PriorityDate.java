package com.notion.notionapi.controller.dto.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDate {
    private String id;
    private String type;

    @JsonProperty("date")
    private PropertiesDate propertiesDates;

}
