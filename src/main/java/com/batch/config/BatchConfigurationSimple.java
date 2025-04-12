package com.batch.config;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.entity.Person;
import com.batch.entity.PersonAge;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BatchConfigurationSimple {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Person> reader() {
        return new JdbcCursorItemReaderBuilder<Person>()
                .dataSource(dataSource)
                .name("personReader")
                .sql("SELECT persons_id, name, last_name, birthday FROM persons")
                .rowMapper(new BeanPropertyRowMapper<>(Person.class))
                .build();
    }

    @Bean
    public ItemProcessor<Person, PersonAge> processor() {
        return person -> {
            PersonAge personAge = new PersonAge();
            personAge.setName(person.getName());
            personAge.setLastName(person.getLastName());
            personAge.setAge(calculateAge(person.getBirthday()));
            return personAge;
        };
    }

    private int calculateAge(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(birthday, formatter);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Bean
    public JdbcBatchItemWriter<PersonAge> writer() {
        return new JdbcBatchItemWriterBuilder<PersonAge>()
                .dataSource(dataSource)
                .sql("INSERT INTO persons_age (name, last_name, age) VALUES (:name, :lastName, :age)")
                .beanMapped()
                .build();
    }


    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<Person, PersonAge>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean(name = "jobSimple")
    public Job jobSimple(JobRepository jobRepository, Step step) {
        return new JobBuilder("jobSimple", jobRepository)
                .start(step)
                .build();
    }
}