package com.batch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "persons_age")
public class PersonAge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persons_age_id")
    private int personsAgeId;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;

}
