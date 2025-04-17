package com.batch.steps.chunk.impl;

import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.batch.entity.Person;
import com.batch.service.impl.PersonServiceImpl;
import com.batch.steps.chunk.inter.IItemReaderService;

@Component
public class ItemReaderServiceImpl implements IItemReaderService<Person>{


    private final PersonServiceImpl personService;
    private Iterator<Person> iterator = null;
    private Integer offset = 0;

    
    public ItemReaderServiceImpl(PersonServiceImpl personService){
        this.personService = personService;
    }


    @Override
    public Person catchRead() {
        if (iterator == null || !iterator.hasNext()){
            var listPerson = personService.getAll(offset, 10);
            if (listPerson.isEmpty()) {
                return null;
            }
            iterator = listPerson.iterator();
            offset++;
        }
        return iterator.hasNext() ? iterator.next() : null;
    }


    
}
