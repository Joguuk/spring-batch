package me.jjozerg.baseballbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BaseballBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseballBatchApplication.class, args);
    }
}
