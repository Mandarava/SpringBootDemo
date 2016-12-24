package com.ztc.controller;

import com.ztc.dao.PersonRepository;
import com.ztc.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zt on 2016/12/24.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/person")
    public List<Person> personList(){
        return personRepository.findAll();
    }

    @PostMapping(value = "/person")
    public Person personAdd(@RequestParam("name") String name,
                            @RequestParam("age") Integer age){
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person = personRepository.save(person);
        return person;
    }

    @GetMapping(value="/person/{id}")
    public Person personFindOne(@PathVariable("id") Integer id){
        return  personRepository.findOne(id);
    }

    @PutMapping(value="/person/{id}")
    public Person personUpdate(@PathVariable("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("age") Integer age){
        Person person = new Person();
        person.setId(id);
        person.setAge(age);
        person.setName(name);
        return personRepository.save(person);
    }

    @DeleteMapping(value = "/person/{id}")
    public void personDelete(@PathVariable("id") Integer id){
            personRepository.delete(id);
    }

    @GetMapping(value="/person/age/{age}")
    public List<Person> personListByAge(@PathVariable("age") Integer age){
        return  personRepository.findByAge(age);
    }

}