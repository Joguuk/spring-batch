package me.jjozerg.baseballbatch.job;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : me.jjozerg.baseballbatch.job
 * fileName : BaseballJobConfigurationTest
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
//@ContextConfiguration(classes = BaseballJobConfiguration.class)
class BaseballJobConfigurationTest {
    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void job_test() throws Exception {
        //given
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        //when
        //then
        Assertions.assertThat(jobExecution.getExitStatus().getExitCode())
                .as("job test")
                .isEqualTo("COMPLETED");
    }

}