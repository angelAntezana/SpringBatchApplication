package com.batch.steps.chunk;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.batch.entity.PersonAge;
import com.batch.persistence.IPersonAgeDao;

@Component
public class ItemChunkWriter implements ItemWriter<PersonAge>{

    @Autowired
    IPersonAgeDao personAgeDao;

    @Override
    public void write(@NonNull Chunk<? extends PersonAge> chunk) throws Exception {
        personAgeDao.saveAll(chunk.getItems());
    }


    
}
