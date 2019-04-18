package com.company.springboot.controller.additional;

import com.company.springboot.entity.Person;
import com.company.springboot.entity.Phone;
import com.company.springboot.service.additional.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonRestController {

    private final PersonService service;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.service = personService;
    }

    @GetMapping()
    List<Person> getPeople() {
        return service.getPeople();
    }

    @PostMapping("/save")
    Person savePerson(@RequestParam String name, @RequestParam Integer age) {
        return service.savePerson(name, age);
    }

    @GetMapping("/{id}")
    Person getParson(@PathVariable Long id) {
        return service.getParson(id);
    }

    @PutMapping("/update")
    Person updatePerson(@RequestParam Long id, @RequestParam String name, @RequestParam Integer age) {
        return service.updatePerson(id, name, age);
    }

    @DeleteMapping("/delete")
    void deletePerson(@RequestParam Long id) {
        service.deletePerson(id);
    }

    @GetMapping("/findByNameIgnoreCase/{name}")
    List<Person> findByNameIgnoreCase(@PathVariable String name) {
        return service.findByNameIgnoreCase(name);
    }

    @GetMapping("/findByNameAndAge/{req}")
    List<Person> findByNameAndAge(@PathVariable String req) {
        return service.findByNameAndAge(req);
    }

    @GetMapping("/findByAgeOrderByName/{age}")
    List<Person> findByAgeOrderByName(@PathVariable Integer age) {
        return service.findByAgeOrderByName(age);
    }

    @GetMapping("/findByNameLike/{name}")
    List<Person> findByNameLike(@PathVariable String name) {
        return service.findByNameLike(name);
    }

    @GetMapping("/findByAgeBetween/{req}")
    List<Person> findByAgeBetween(@PathVariable String req) {
        return service.findByAgeBetween(req);
    }

    @GetMapping("/phones")
    List<Phone> findAllPhones() {
        return service.findAllPhones();
    }

    @GetMapping("/phones/{name}")
    List<Phone> findAllPhones(@PathVariable String name) {
        return service.findAllPhones(name);
    }

}
