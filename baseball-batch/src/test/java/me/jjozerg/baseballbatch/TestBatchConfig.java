package me.jjozerg.baseballbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * packageName : me.jjozerg
 * fileName : TestBatchConfig
 * author : joguk
 * date : 2021/11/22
 * description :
 * <pre></pre>
 * ===========================================================
 * DATE AUTHOR NOTE
 * 2021/11/22 joguk 최초 생성
 * -----------------------------------------------------------
 */
@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
@ComponentScan(basePackages = "me.jjozerg.baseballbatch.job")
public class TestBatchConfig {

}
