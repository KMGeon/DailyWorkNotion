package com.example.dailyworknotion.controller.dto.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class Properties {
    @JsonProperty("Name")
    private TitleProperty name;
}
