package me.jozerg.batchpractice.jobs;

import lombok.RequiredArgsConstructor;
import me.jozerg.batchpractice.tasklets.BatchTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory; // Job 빌더 생성
    private final StepBuilderFactory stepBuilderFactory; // Step 빌더 생성

    // JobBuilderFactory를 통해서 tutorialJob을 생성
    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("practiceJob")
                .start(practiceStep())
                .build();
    }

    // StepBuilderFactory를 통해서 practiceStep을 생성
    @Bean
    public Step practiceStep() {
        return stepBuilderFactory.get("practiceStep")
                .tasklet(new BatchTasklet())
                .build();
    }
}
