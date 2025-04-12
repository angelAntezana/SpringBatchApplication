package com.batch.steps.chunk;

import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.stereotype.Component;

import com.batch.entity.Person;

import jakarta.persistence.EntityManagerFactory;

@Component
public class ItemChunkReader extends JpaPagingItemReader<Person> {

    
    public ItemChunkReader(EntityManagerFactory entityManagerFactory) {
        this.setEntityManagerFactory(entityManagerFactory);
        this.setQueryString("SELECT p FROM Person p");
        this.setPageSize(10);
        this.setSaveState(false);
        this.setName("personReader");
    }
    
}
