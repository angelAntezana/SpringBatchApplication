package com.batch.steps.chunk;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.batch.entity.Person;
import com.batch.entity.PersonAge;

@Component
public class ItemChunkProcessor implements ItemProcessor<Person, PersonAge>{

    @Override
    @Nullable
    public PersonAge process(@NonNull Person person) throws Exception {
            PersonAge personAge = PersonAge.builder()
            .name(person.getName())
            .lastName(person.getLastName())
            .age(PersonAge.calculateAge(person.getBirthday()))
            .build();
        return personAge;
    }

    // @Override
    // @Nullable
    // public PersonAge process(@NonNull Person person) throws Exception {
        

    //         PersonAge personAge = PersonAge.builder()
    //         .name(person.getName())
    //         .lastName(person.getLastName())
    //         .age(PersonAge.calculateAge(person.getBirthday()))
    //         .build();
    //     return personAge;
    // }
    
}
