package com.batch.persistence;

import com.batch.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDao extends CrudRepository<Person, Long> {
}
