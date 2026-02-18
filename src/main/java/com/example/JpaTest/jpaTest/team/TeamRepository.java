package com.example.JpaTest.jpaTest.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select distinct t from Team t join fetch t.memberList")
    List<Team> findAllInnerFetch();

    @Query("select t from Team t left join fetch t.memberList")
    List<Team> findAllOuterFetch();

    @Query("select t from Team t join fetch t.memberList")
    List<Team> findAllTeam();
}
