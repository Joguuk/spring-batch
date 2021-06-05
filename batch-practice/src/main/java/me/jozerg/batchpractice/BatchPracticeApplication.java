package me.jozerg.batchpractice;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling       // 스케쥴링 활성화
@SpringBootApplication
@EnableBatchProcessing  // 배치 기능 활성화
public class BatchPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchPracticeApplication.class, args);
    }

}
