package com.zerocamel.tx;

import com.zerocamel.tx.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-10 16:16
 **/
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void insertUser()
    {
        userDao.insert();
        System.out.println("插入完成！");
    }

}
