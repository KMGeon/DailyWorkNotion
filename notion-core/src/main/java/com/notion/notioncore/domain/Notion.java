package com.notion.notioncore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Notion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String request_id;

    private String title;

    //  ==================================================  properties 시작 ==================================================
    private String priority;

    private String category;

    private String status;

    private String processing;

    private String request;

    private String requestDate;

    private String deadLine;

    //  ==================================================  properties 끝 ==================================================

    private LocalDateTime created_time;

    private LocalDateTime last_edited_time;

    @Builder
    public Notion(String request_id, String title, String priority, String category, String status, String processing, String request, String requestDate, String deadLine, LocalDateTime created_time, LocalDateTime last_edited_time) {
        this.request_id = request_id;
        this.title = title;
        this.priority = priority;
        this.category = category;
        this.status = status;
        this.processing = processing;
        this.request = request;
        this.requestDate = requestDate;
        this.deadLine = deadLine;
        this.created_time = created_time;
        this.last_edited_time = last_edited_time;
    }

    protected Notion() {

    }
}
