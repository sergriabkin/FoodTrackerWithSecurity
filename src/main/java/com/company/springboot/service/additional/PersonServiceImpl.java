package com.company.springboot.service.additional;

import com.company.springboot.entity.Person;
import com.company.springboot.entity.Phone;
import com.company.springboot.repository.additional.PersonRepository;
import com.company.springboot.repository.additional.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository, PhoneRepository phoneRepository) {
        this.repository = repository;
        this.phoneRepository = phoneRepository;
    }
    @Override
    public List<Person> getPeople() {
        return repository.findAll();
    }

    @Override
    public Person savePerson(@RequestParam String name, @RequestParam Integer age) {
        Person person = new Person(name, age);
        return repository.save(person);
    }

    @Override
    public Person getParson(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Person updatePerson(@RequestParam Long id, @RequestParam String name, @RequestParam Integer age) {
        repository.deleteById(id);
        return repository.save(new Person(name, age));
    }

    @Override
    public void deletePerson(@RequestParam Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Person> findByNameIgnoreCase(@PathVariable String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Person> findByNameAndAge(@PathVariable String req) {
        String[] params = req.split("&");
        return repository.findByNameAndAge(params[0], Integer.valueOf(params[1]));
    }

    @Override
    public List<Person> findByAgeOrderByName(@PathVariable Integer age) {
        return repository.findByAgeOrderByName(age);
    }

    @Override
    public List<Person> findByNameLike(@PathVariable String name) {
        return repository.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Person> findByAgeBetween(@PathVariable String req) {
        String[] params = req.split("&");
        return repository.findByAgeBetween(Integer.valueOf(params[0]), Integer.valueOf(params[1]));
    }

    @Override
    public List<Phone> findAllPhones() {
        return phoneRepository.findAll();
    }

    @Override
    public List<Phone> findAllPhones(@PathVariable String name) {
        return phoneRepository.findAllByPersonNameOrderByNumber(name);
    }



}
