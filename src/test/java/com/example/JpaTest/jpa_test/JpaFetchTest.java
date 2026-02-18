package com.example.JpaTest.jpa_test;

import com.example.JpaTest.jpaTest.member.Member;
import com.example.JpaTest.jpaTest.member.MemberRepository;
import com.example.JpaTest.jpaTest.team.Team;
import com.example.JpaTest.jpaTest.team.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class JpaFetchTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("팀을 조회할 때 MemberList를 EAGER로 조회")
    void test1() {

        teamRepository.findAll();
    }

    @Test
    @DisplayName("팀을 조회할 때 MemberList를 LAZY로 조회")
    void test() {

        teamRepository.findAll();
    }

    @Test
    @Transactional
    @DisplayName("팀을 조회할 때 MemberList를 LAZY로 조회하되, 각 멤버를 조회")
    void test3() {
        List<Team> teams = teamRepository.findAll();

        for (Team team: teams) {
            List<Member> memberList = team.getMemberList();

            for (Member member: memberList) {
                System.out.println(member.getMemberName());
            }
        }
    }

    @Test
    @DisplayName("left join fetch와 inner join fetch 쿼리 비교")
    void test4() {
        teamRepository.findAllInnerFetch();

        teamRepository.findAllOuterFetch();
    }

    @Test
    @DisplayName("member 조회")
    void test5() {
        memberRepository.findAll();
    }

}
