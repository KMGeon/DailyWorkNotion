package com.notion.notionapi.controller.dto.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriorityPeople {
    private String id;
    private String type;

    @JsonProperty("people")
    private List<People> people;
}
