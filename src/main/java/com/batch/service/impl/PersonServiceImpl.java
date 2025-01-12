package com.batch.service.impl;

import com.batch.entity.Person;
import com.batch.persistence.IPersonDao;
import com.batch.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonDao personDao;
    @Override
    @Transactional
    public void saveAll(List<Person> personList) {
        personDao.saveAll(personList);
    }
}
