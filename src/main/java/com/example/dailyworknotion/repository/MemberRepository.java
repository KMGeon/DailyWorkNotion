package com.example.dailyworknotion.repository;

import com.example.dailyworknotion.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
