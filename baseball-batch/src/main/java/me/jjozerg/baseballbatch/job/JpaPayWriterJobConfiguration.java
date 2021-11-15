package me.jjozerg.baseballbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jjozerg.baseballbatch.entity.Pay;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JpaPayWriterJobConfiguration {
    public static final String BEAN_PREFIX = "JpaPayWriter";
    public static final String JOB_NAME = BEAN_PREFIX + "job";
    private static final String STEP_NAME = BEAN_PREFIX + "step";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Value("${chunkSize:1000}")
    private int chunkSize;

    @Bean(JOB_NAME)
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .preventRestart()
                .start(step())
                .build();
    }

    @Bean(STEP_NAME)
    public Step step() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Pay, Pay>chunk(chunkSize)
                .reader(JpaPayReader())
                .processor(JpaPayProcessor())
                .writer(JpaPayWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Pay> JpaPayReader() {
        return new JpaPagingItemReaderBuilder<Pay>()
                .name("JpaPayReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT p FROM Pay p")
                .build();
    }

    @Bean
    public ItemProcessor<Pay, Pay> JpaPayProcessor() {
        return item -> {
            log.info("item -> {}", item);
            return new Pay(item.getAmount()+1000, item.getTxName(), item.getTxDateTime());
        };
    }

    private JpaItemWriter<Pay> JpaPayWriter() {
        JpaItemWriter jpaItemWriter = new JpaItemWriter();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
}
