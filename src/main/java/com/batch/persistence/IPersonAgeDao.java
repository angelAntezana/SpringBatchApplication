package com.batch.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch.entity.PersonAge;

@Repository
public interface IPersonAgeDao extends JpaRepository<PersonAge, Long>{

    
}
