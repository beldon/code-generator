package me.beldon.module.generate.service.impl;

import me.beldon.module.generate.dao.ConnectDbRepository;
import me.beldon.module.generate.domain.ConnectDb;
import me.beldon.module.generate.service.IConnectDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/21, All Rights Reserved.
 * http://beldon.me
 */
@Service
public class ConnectDbService implements IConnectDbService {

    @Autowired
    private ConnectDbRepository connectDbRepository;

    @Override
    public void save(ConnectDb connectDb) {
        connectDbRepository.save(connectDb);
    }

    @Override
    public List<ConnectDb> findAll() {
        return connectDbRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        connectDbRepository.delete(id);
    }
}
