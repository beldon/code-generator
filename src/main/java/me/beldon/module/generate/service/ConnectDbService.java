package me.beldon.module.generate.service;

import me.beldon.module.generate.bean.ConnectDb;

import java.util.List;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/21, All Rights Reserved.
 * http://beldon.me
 */
public interface ConnectDbService {

    void save(ConnectDb connectDb);

    List<ConnectDb> findAll();

    void deleteById(String id);

    void clearAll();
}
