package me.jjozerg.baseballbatch.job;

import me.jjozerg.baseballbatch.TestBatchConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : me.jjozerg.baseballbatch.job
 * fileName : JpaPayWriterJobConfigurationTest
 * author : joguk
 * date : 2021/11/22
 * description :
 * <pre></pre>
 * ===========================================================
 * DATE AUTHOR NOTE
 * 2021/11/22 joguk 최초 생성
 * -----------------------------------------------------------
 */

@RunWith(SpringRunner.class)
@SpringBatchTest
@SpringBootTest(classes = {JpaPayWriterJobConfiguration.class, TestBatchConfig.class})
class JpaPayWriterJobConfigurationTest {
    private JobLauncherTestUtils jobLauncherTestUtils = getJobLauncherTestUtils();

    @Bean
    public JobLauncherTestUtils getJobLauncherTestUtils(){
        return new JobLauncherTestUtils() {
            @Override
            @Autowired
            public void setJob(@Qualifier("myjobname") Job job) {
                super.setJob(job);
            }
        };
    }

    @Test
    public void job_test() throws Exception {
        //given
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        //when
        ExitStatus exitStatus = jobExecution.getExitStatus();
        //then
        Assertions.assertThat(exitStatus)
                .as("job 결과")
                .isEqualTo("COMPLETED");
    }

}