package com.ztc;

import com.ztc.entity.Person;
import com.ztc.repository.PersonMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zt on 2017/3/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

    @Autowired
    PersonMapper personMapper;

    @Test
    public void test2() {
        List<Person> persons = personMapper.findPersonList();
        System.out.println(persons.size());
    }

}
