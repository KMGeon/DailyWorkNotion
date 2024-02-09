package com.notion.notionapi.controller.dto.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class Properties {

    @JsonProperty("우선순위")
    private PrioritySelect priority;

    @JsonProperty("분류")
    private PrioritySelect category;

    @JsonProperty("상태")
    private PrioritySelect status;

    @JsonProperty("처리")
    private PriorityPeople processing;

    @JsonProperty("요청")
    private PriorityPeople request;

    @JsonProperty("요청날짜")
    private PriorityDate requestDate;

    @JsonProperty("기한")
    private PriorityDate deadline;

    @JsonProperty("Name")
    private TitleProperty name;
}
