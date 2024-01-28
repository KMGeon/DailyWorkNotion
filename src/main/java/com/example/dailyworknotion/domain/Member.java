package com.example.dailyworknotion.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
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

    private LocalTime officeGoHour;

    private boolean isAcceptEmail;

    @Builder
    public Member(Long id, String email, LocalTime officeGoHour, boolean isAcceptEmail) {
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
