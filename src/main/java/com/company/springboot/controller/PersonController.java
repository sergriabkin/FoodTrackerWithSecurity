package com.company.springboot.controller;

import com.company.springboot.entity.Person;
import com.company.springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/personsform")
    public String persons(Map<String, Object> model) {
        List<Person> persons = service.getPeople();

        model.put("persons", persons);

        return "personsform";
    }

    @PostMapping("/personsform")
    public String add(@RequestParam String name, @RequestParam Integer age, Map<String, Object> model) {

        service.savePerson(name, age);

        List<Person> persons = service.getPeople();

        model.put("persons", persons);

        return "personsform";
    }

    @PostMapping("/personsfilter")
    public String filter(@RequestParam String personsfilter, Map<String, Object> model) {
        List<Person> persons;

        if (personsfilter != null && !personsfilter.isEmpty()) {
            persons = service.findByNameIgnoreCase(personsfilter);
        } else {
            persons = service.getPeople();
        }

        model.put("persons", persons);

        return "personsform";
    }

}
