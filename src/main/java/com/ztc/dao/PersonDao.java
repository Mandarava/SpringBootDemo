package com.ztc.dao;

import com.ztc.entity.Person;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zt on 2017/3/27.
 */
@Mapper
public interface PersonDao {

    @Select("SELECT * FROM PERSON ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
    })
    List<Person> findPerson();
}
