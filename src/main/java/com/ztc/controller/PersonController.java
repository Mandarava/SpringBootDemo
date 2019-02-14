package com.ztc.controller;

import com.ztc.dao.PersonRepository;
import com.ztc.entity.Person;
import com.ztc.entity.Result;
import com.ztc.utils.ResultUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;


/**
 * Created by zt on 2016/12/24.
 */
@RestController
public class PersonController {

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/person")
    public List<Person> personList() {
        return personRepository.findAll();
    }

    @PostMapping(value = "/person")
    public Result<Person> personAdd(@Valid Person person) {
        return ResultUtil.success(personRepository.save(person));
    }

    @GetMapping(value = "/person/{id}")
    public Person personFindOne(@PathVariable("id") Integer id) {
        return personRepository.findOne(id);
    }

    @PutMapping(value = "/person/{id}")
    public Person personUpdate(@PathVariable("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("age") Integer age) {
        Person person = new Person();
        person.setId(id);
        person.setAge(age);
        person.setName(name);
        return personRepository.save(person);
    }

    @DeleteMapping(value = "/person/{id}")
    public void personDelete(@PathVariable("id") Integer id) {
        personRepository.delete(id);
    }

    @GetMapping(value = "/person/age/{age}")
    public List<Person> personListByAge(@PathVariable("age") Integer age) {
        return personRepository.findByAge(age);
    }

    @GetMapping(value = "/person/getAge/{id}")
    public Integer getAge(@PathVariable("id") Integer id) {
        Person person = personRepository.findOne(id);
        return person.getAge();
    }

}
