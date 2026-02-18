# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 언어 규칙

- 모든 결과값, 설명, 주석, 커밋 메시지, PR 설명은 반드시 **한글**로 작성한다.
- 코드 내 변수명, 메서드명 등 식별자는 영문을 유지하되, 그 외 사람이 읽는 텍스트는 한글을 사용한다.

## Build & Test Commands

```bash
# Build
./gradlew build

# Run application
./gradlew bootRun

# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "com.example.JpaTest.jpa_cascade_test.JpaCascadeTest"

# Run a single test method
./gradlew test --tests "com.example.JpaTest.jpa_test.JpaFetchTest.fetchTest"
```

## Prerequisites

- Java 17
- MySQL running on localhost:3306 with database `jpa_test`

## Architecture

Spring Boot 3.5.7 project for exploring JPA/Hibernate patterns. No controller/service layers — entities and repositories are tested directly via `@SpringBootTest` test classes.

**Domain model:** `Team (1) ←→ (*) Member` — a one-to-many relationship where Member owns the foreign key (`@ManyToOne(fetch = LAZY)`). Team has a convenience method `teamSave()` that sets both sides of the bidirectional relationship.

**Packages under `com.example.JpaTest.jpaTest`:**
- `team/` — `Team` entity + `TeamRepository` (custom JPQL with inner/outer join fetch queries)
- `member/` — `Member` entity + `MemberRepository`
- `batch_test/` — `BatchTest` entity + `SnowflakeIdGenerator` (custom distributed ID generator: 41-bit timestamp / 10-bit server ID / 12-bit sequence)

**Test classes** (in `src/test/java/com/example/JpaTest/`): Each test class focuses on a specific JPA concept — cascade persistence, lazy/eager fetch strategies, join fetch with/without distinct, and collection membership.
