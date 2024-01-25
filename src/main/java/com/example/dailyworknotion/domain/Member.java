package com.example.dailyworknotion.domain;

import com.example.dailyworknotion.validGroup.ToContentValidGroup;
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

    private String email;

    private LocalDateTime officeGoHour;

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
