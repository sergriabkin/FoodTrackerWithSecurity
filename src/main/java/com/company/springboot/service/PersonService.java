package com.company.springboot.service;

import com.company.springboot.entity.Person;
import com.company.springboot.entity.Phone;

import java.util.List;

public interface PersonService {

    List<Person> getPeople();

    Person savePerson(String name, Integer age);

    Person getParson(Long id);

    Person updatePerson(Long id, String name, Integer age);

    void deletePerson(Long id);

    List<Person> findByNameIgnoreCase(String name);

    List<Person> findByNameAndAge(String req);

    List<Person> findByAgeOrderByName(Integer age);

    List<Person> findByNameLike(String name);

    List<Person> findByAgeBetween(String req);

    List<Phone> findAllPhones();

    List<Phone> findAllPhones(String name);

}
