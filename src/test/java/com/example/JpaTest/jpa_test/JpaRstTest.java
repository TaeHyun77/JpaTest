package com.example.JpaTest.jpa_test;

import com.example.JpaTest.jpaTest.member.Member;
import com.example.JpaTest.jpaTest.member.MemberRepository;
import com.example.JpaTest.jpaTest.team.Team;
import com.example.JpaTest.jpaTest.team.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class JpaRstTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    @Transactional
    void setUp() {

        Team t = new Team("one");

        for (int i = 0; i < 2; i++) {
            Member member = Member.builder()
                    .memberName(String.valueOf(i))
                    .team(t)
                    .build();

            t.teamSave(member);
        }

        teamRepository.save(t);
    }

    @Test
    @DisplayName("fetch join 시 distinct 없이")
    public void noDistinct() {

        List<Team> teamList = teamRepository.findAllTeam();

        System.out.println(teamList);
    }
}
