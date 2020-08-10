package com.zerocamel.tx;

import com.zerocamel.tx.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-10 16:16
 **/
@EnableTransactionManagement
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public void insertUser()
    {
        userDao.insert();
        System.out.println("插入完成！");

    }

}
