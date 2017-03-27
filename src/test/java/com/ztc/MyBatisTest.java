package com.ztc;

import com.ztc.dao.PersonDao;
import com.ztc.dao.PersonMapper;
import com.ztc.entity.Person;

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
    PersonDao personDao;

    @Autowired
    PersonMapper personMapper;

    @Test
    public void test() {
        List<Person> persons = personDao.findPerson();
    }

    @Test
    public void test2() {
        List<Person> persons = personMapper.findPersonList();
    }
}
