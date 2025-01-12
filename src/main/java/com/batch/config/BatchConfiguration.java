package com.batch.config;

import com.batch.steps.ItemDecompressStep;
import com.batch.steps.ItemProcessorStep;
import com.batch.steps.ItemReaderStep;
import com.batch.steps.ItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {



    @Bean
    @JobScope
    public ItemDecompressStep itemDecompressStep() {
        return new ItemDecompressStep();
    }

    @Bean
    @JobScope
    public ItemReaderStep itemReaderStep() {
        return new ItemReaderStep();
    }

    @Bean
    @JobScope
    public ItemProcessorStep itemProcessorStep() {
         return new ItemProcessorStep();
    }

    @Bean
    @JobScope
    public ItemWriterStep itemWriterStep() {
        return  new ItemWriterStep();
    }


    @Bean
    public Step descompressFileStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("descompressFileStep", jobRepository)
                .tasklet(itemDecompressStep(), transactionManager)
                .build();
    }

    @Bean
    public Step readPersonStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("readPersonStep", jobRepository)
                .tasklet(itemReaderStep(), transactionManager)
                .build();
    }

    @Bean
    public Step processPersonStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("processPerson", jobRepository)
                .tasklet(itemProcessorStep(), transactionManager)
                .build();
    }

    @Bean
    public Step writePersonStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("writePersonStep", jobRepository)
                .tasklet(itemWriterStep(), transactionManager)
                .build();
    }

    @Bean
    public Job readCSVJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("readCSVJob", jobRepository)
                .start(descompressFileStep(jobRepository, transactionManager))
                .next(readPersonStep(jobRepository, transactionManager))
                .next(processPersonStep(jobRepository, transactionManager))
                .next(writePersonStep(jobRepository, transactionManager))
                .build();
    }
}
