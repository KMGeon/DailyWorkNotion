package com.example.dailyworknotion.controller.dto.notion;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Annotations {
    private boolean bold;
    private boolean italic;
    private boolean strikethrough;
    private boolean underline;
    private boolean code;
    private String color;
}
