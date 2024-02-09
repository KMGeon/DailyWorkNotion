package com.notion.notioncore.exception.member;

import com.notion.notioncore.exception.MemberAbstractException;

public class DuplicateEmail extends MemberAbstractException {
    public DuplicateEmail() {
    }

    public DuplicateEmail(String message) {
        super(message);
    }

    public DuplicateEmail(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
