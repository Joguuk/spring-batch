package me.jjozerg.baseballbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class BaseballJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job baseballJob() {
        log.info(">>>>> BaseballJob");
        return jobBuilderFactory.get("baseballJob")
                .start(baseballStep1(null))
                .next(baseballStep2(null))
                .build();
    }

    @Bean
    @JobScope
    public Step baseballStep1(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("baseballStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> baseballStep1");
                    log.info(">>>>> requestDate => {}", requestDate);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @JobScope
    public Step baseballStep2(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("baseballStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> baseballStep2");
                    log.info(">>>>> requestDate => {}", requestDate);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
