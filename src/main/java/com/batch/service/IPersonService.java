package com.batch.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.batch.entity.Person;

public interface IPersonService {

    void saveAll (List<Person> personList);

    Page<Person> getAll(Integer offset, Integer limit);
}
