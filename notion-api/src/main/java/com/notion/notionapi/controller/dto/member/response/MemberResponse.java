package com.notion.notionapi.controller.dto.member.response;

import com.notion.notioncore.domain.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Builder
public class MemberResponse {
    private Long id;

    private String email;

    private boolean isAcceptEmail;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goOfficeHour;

    public static MemberResponse toContent(Member member) {
        return MemberResponse.builder()
                .email(member.getEmail())
                .isAcceptEmail(member.isAcceptEmail())
                .goOfficeHour(member.getOfficeGoHour())
                .build();
    }
}
