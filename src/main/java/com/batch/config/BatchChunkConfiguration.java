package com.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.entity.Person;
import com.batch.entity.PersonAge;
import com.batch.steps.chunk.ItemChunkProcessor;
import com.batch.steps.chunk.ItemChunkReader;
import com.batch.steps.chunk.ItemChunkWriter;

@Configuration
public class BatchChunkConfiguration {
    
    @Autowired
    private ItemChunkReader itemChunkReader;
    @Autowired
    private ItemChunkProcessor itemChunkProcessor;
    @Autowired
    private ItemChunkWriter itemChunkWriter;

    @Bean
    protected Step processPersons(JobRepository jobRepository,
    PlatformTransactionManager transactionManager) {

        return new StepBuilder("processPersons", jobRepository)
        .<Person, PersonAge> chunk(10, transactionManager)
        .reader(itemChunkReader)
        .processor(itemChunkProcessor)
        .writer(itemChunkWriter)
        .build();
    }

    @Bean(name = "jobChunk")
    public Job jobChunk(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("jobChunk", jobRepository)
                .start(processPersons(jobRepository, transactionManager))
                .build();
    }
}
