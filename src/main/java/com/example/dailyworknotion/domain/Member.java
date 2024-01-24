package com.example.dailyworknotion.domain;

import com.example.dailyworknotion.validGroup.ToTargetValidationGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, length = 50)
    private final String memberKey = UUID.randomUUID().toString();

    @NotNull(groups = ToTargetValidationGroup.class, message = "이메일을 입력하세요.")
    private String email;

    @NotNull(groups = ToTargetValidationGroup.class, message = "출근 시간을 입력하세요.")
    private LocalDateTime officeGoHour;

    @NotNull(groups = ToTargetValidationGroup.class, message = "수신여부를 선택하세요.")
    private boolean isAcceptEmail;

    @Builder
    public Member(Long id, String email, LocalDateTime officeGoHour, boolean isAcceptEmail) {
        this.id = id;
        this.email = email;
        this.officeGoHour = officeGoHour;
        this.isAcceptEmail = isAcceptEmail;
    }

    public static Member of(Member member) {
        return Member.builder()
                .email(member.getEmail())
                .isAcceptEmail(member.isAcceptEmail)
                .officeGoHour(member.getOfficeGoHour())
                .build();
    }
}
