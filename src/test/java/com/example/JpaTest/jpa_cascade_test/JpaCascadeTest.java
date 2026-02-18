package com.example.JpaTest.jpa_cascade_test;

import com.example.JpaTest.jpaTest.member.Member;
import com.example.JpaTest.jpaTest.member.MemberRepository;
import com.example.JpaTest.jpaTest.team.Team;
import com.example.JpaTest.jpaTest.team.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaCascadeTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("team의 cascade가 PERSIST 일 떄, 저장 테스트")
    public void saveTest() {

        Team team1 = new Team("team1");

        Member member1 = Member.builder()
                .memberName("m1")
                .team(team1)
                .build();

        Member member2 = Member.builder()
                .memberName("m2")
                .team(team1)
                .build();

        teamRepository.save(team1);
    }
}
