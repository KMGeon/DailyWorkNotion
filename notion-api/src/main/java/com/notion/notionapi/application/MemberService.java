package com.notion.notionapi.application;

import com.notion.notionapi.controller.dto.member.response.MemberResponse;
import com.notion.notioncore.domain.Member;
import com.notion.notioncore.exception.member.DuplicateEmail;
import com.notion.notioncore.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Transactional
    public MemberResponse signUp(Member requestMember) {

        Member member = Member.of(requestMember);
        boolean existDuplicateMember = memberRepository.existsByEmail(member.getEmail());

        if (existDuplicateMember) {
            throw new DuplicateEmail(String.format("==== [%s] ===== %s 이메일이 중복이 되었습니다.",
                    this.getClass().getSimpleName(),
                    member.getEmail()));
        }

        logger.info("====== /notion/database [{}.member : {}]", this.getClass().getSimpleName(), member);
        Member saveMember = memberRepository.save(member);

        return MemberResponse.toContent(saveMember);
    }
}
