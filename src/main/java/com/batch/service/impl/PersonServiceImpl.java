package com.batch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.batch.entity.Person;
import com.batch.persistence.IPersonDao;
import com.batch.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonDao personDao;
    @Override
    @Transactional
    public void saveAll(List<Person> personList) {
        personDao.saveAll(personList);
    }
    @Override
    public Page<Person> getAll(Integer offset, Integer limit) {
        return personDao.findAll(PageRequest.of(offset, limit));
    }
}
