package com.example.JpaTest.jpa_batch_test;

import com.example.JpaTest.jpaTest.batch_test.BatchTest;
import com.example.JpaTest.jpaTest.batch_test.BatchTestRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BatchInsertUpdateTest {

    @Autowired
    private BatchTestRepository batchTestRepository;

    @Autowired
    private EntityManager entityManager;

    @DisplayName("id를 직접 설정했을 때, Batch Insert가 이루어지는지 확인 - save()")
    @Test
    public void test1() {

        for (int i = 0; i < 10; i++) {
            BatchTest b = new BatchTest(i, i + "-name");

            batchTestRepository.save(b);
        }
    }

    @Transactional
    @DisplayName("id를 직접 설정했을 때, Batch Insert가 이루어지는지 확인 - saveAll()")
    @Test
    public void test2() {
        List<BatchTest> bl = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            bl.add(new BatchTest(i, i + "-name"));
        }

        batchTestRepository.saveAll(bl);

        entityManager.flush();
    }

    @Transactional
    @DisplayName("id를 직접 설정했을 때, Batch Update가 이루어지는지 확인")
    @Test
    public void test3() {
        List<BatchTest> bl = batchTestRepository.findAll();

        for (BatchTest b : bl) {
            b.setName("update01");
        }

        entityManager.flush();
    }

    /*
    * 배치 Insert 사용 O : BatchTest가 BaseEntity를 상속했을 때
    * 배치 Insert 사용 X : BatchTest가 BaseEntity를 상속하지 않고 IDENTITY 전략 사용 후 application.properties에서 batch 설정 제거
    * */
    @Transactional
    @DisplayName("배치 insert 성능 비교")
    @Test
    public void batchInsertWithoutBatch() {
        int count = 10000;

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            batchTestRepository.save(new BatchTest(i, i + "-name"));
        }
        entityManager.flush();

        long end = System.currentTimeMillis();
        System.out.println("배치 insert " + count + "건 소요시간: " + (end - start) + "ms");
    }
}