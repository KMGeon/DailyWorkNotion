package com.example.dailyworknotion.repository;

import com.example.dailyworknotion.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT " +
            "CASE WHEN COUNT(m) > 0 THEN true " +
            "ELSE false END " +
            "FROM Member m " +
            "WHERE m.email = :email")
    boolean existsByEmail(String email);
}
