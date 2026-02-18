package com.example.JpaTest.jpaTest.member;

import com.example.JpaTest.jpaTest.team.Team;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;
}
