package com.example.JpaTest.jpaTest.team;

import com.example.JpaTest.jpaTest.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "team")
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<Member> memberList = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void teamSave(Member member) {
        member.setTeam(this);
        memberList.add(member);
    }
}
