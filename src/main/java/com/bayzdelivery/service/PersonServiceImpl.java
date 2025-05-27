package com.bayzdelivery.service;


import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    PersonRepository personRepository;


    @Override
    public List<Person> getAll() {
        final List<Person> personList = new ArrayList<>();
        this.personRepository.findAll().forEach(personList::add);
        return personList;
    }


    @Override
    public Person save(final Person p) {
        return this.personRepository.save(p);
    }


    @Override
    public Person findById(final Long personId) {
        final Optional<Person> dbPerson = this.personRepository.findById(personId);
        return dbPerson.orElse(null);
    }
}