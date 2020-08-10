package com.zerocamel.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-10 16:16
 **/
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){

        String sql = "INSERT INTO `user` VALUES(?,?);";
        String substring = UUID.randomUUID().toString().substring(0, 5);
        jdbcTemplate.update(sql,substring,"test");
    }


}
