package com.notion.notioncore.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MemberAbstractException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public MemberAbstractException() {
    }

    public MemberAbstractException(String message) {
        super(message);
    }

    public MemberAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}