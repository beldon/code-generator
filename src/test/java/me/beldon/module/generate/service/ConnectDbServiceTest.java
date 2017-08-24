package me.beldon.module.generate.service;

import me.beldon.module.generate.bean.ConnectDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Beldon.
 * Copyright (c)  2017/8/24, All Rights Reserved.
 * https://www.fuyhui.com/
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectDbServiceTest {

    @Autowired
    private ConnectDbService connectDbService;

    @Test
    public void save() throws Exception {
        String host = "localhost";
        String user = "root";
        String pass = "pass";
        ConnectDb connectDb = new ConnectDb();
        connectDb.setHost(host);
        connectDb.setUser(user);
        connectDb.setPass(pass);
        connectDbService.save(connectDb);
    }

    @Test
    public void findAll() throws Exception {
        clearAll();
        for (int i = 0; i < 10; i++) {
            ConnectDb connectDb = new ConnectDb();
            connectDb.setHost("localhost" + i);
            connectDb.setUser("root" + i);
            connectDb.setPass("pass" + i);
            connectDbService.save(connectDb);
        }
        List<ConnectDb> connectDbs = connectDbService.findAll();
        assertEquals(10, connectDbs.size());
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void clearAll() throws Exception {
        connectDbService.clearAll();
        List<ConnectDb> connectDbList = connectDbService.findAll();
        assertEquals(0, connectDbList.size());
    }

}