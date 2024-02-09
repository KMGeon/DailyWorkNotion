package com.example.dailyworknotion.application;

import com.example.dailyworknotion.ContextTest;
import com.example.dailyworknotion.controller.dto.member.response.MemberResponse;
import com.example.dailyworknotion.domain.Member;
import com.example.dailyworknotion.exception.member.DuplicateEmail;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


@Transactional
class MemberServiceTest extends ContextTest {

    private final MemberService memberService;
    private static final String MEMBER_EMAIL = "mugeon@test.com";
    MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    @DisplayName("회원가입")
    void signUpWithValid() throws Exception {
        //given
        LocalTime goOfficeTime = LocalTime.of(9, 0);

        Member givenMember = Member.builder()
                .email(MEMBER_EMAIL)
                .isAcceptEmail(true)
                .officeGoHour(goOfficeTime)
                .build();
        //when
        MemberResponse memberResponse = memberService.signUp(givenMember);

        //then
        assertAll(
                ()->assertThat(memberResponse.getEmail()).isEqualTo(MEMBER_EMAIL),
                ()->assertThat(memberResponse.isAcceptEmail()).isEqualTo(true),
                ()->assertThat(memberResponse.getGoOfficeHour()).isEqualTo(goOfficeTime)
        );
    }

    @Test
    @DisplayName("중복된 이메일 회원가입")
    void duplicateMemberEmail() throws Exception {
        //given
        LocalTime goOfficeTime = LocalTime.of(9, 0);

        Member givenMember = Member.builder()
                .email(MEMBER_EMAIL)
                .isAcceptEmail(true)
                .officeGoHour(goOfficeTime)
                .build();
        //when
        memberService.signUp(givenMember);
        //then
        assertThatThrownBy(()-> memberService.signUp(givenMember))
                .isInstanceOf(DuplicateEmail.class)
                .hasMessage("==== [MemberService] ===== mugeon@test.com 이메일이 중복이 되었습니다.");
    }

    @Test
    @DisplayName("회원가입 실패")
    void shouldSignupFailWhenRequestEmpty() throws Exception {
        //given

        //when
        assertThatThrownBy(()-> memberService.signUp(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Cannot invoke \"com.example.dailyworknotion.domain.Member.getEmail()\" because \"member\" is null");
        //then
    }

}