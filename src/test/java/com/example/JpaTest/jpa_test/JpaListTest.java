package com.example.JpaTest.jpa_test;

import com.example.JpaTest.jpaTest.member.Member;
import com.example.JpaTest.jpaTest.member.MemberRepository;
import com.example.JpaTest.jpaTest.team.Team;
import com.example.JpaTest.jpaTest.team.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class JpaListTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Test
    void setUp() {

        Team t = new Team("one");

        for (int i = 0; i < 3; i++) {
            Member member = Member.builder()
                    .memberName(String.valueOf(i))
                    .team(t)
                    .build();

            member.setTeam(t);
            // t.teamSave(member);
        }

        teamRepository.save(t);

        System.out.println(t.getMemberList().size());
        Assertions.assertEquals(0, t.getMemberList().size());
    }

}
