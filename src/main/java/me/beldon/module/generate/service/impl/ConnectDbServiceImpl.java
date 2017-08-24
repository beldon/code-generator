package me.beldon.module.generate.service.impl;

import me.beldon.module.generate.domain.ConnectDb;
import me.beldon.module.generate.service.ConnectDbService;
import me.beldon.util.ObjectDataUtils;
import me.beldon.util.SSUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/21, All Rights Reserved.
 * http://beldon.me
 */
@Service
public class ConnectDbServiceImpl implements ConnectDbService {

    private final Map<String, ConnectDb> cache = new HashMap<>();

    @Override
    public void save(ConnectDb connectDb) {
        connectDb.setId(SSUtils.getUUID());
        cache.put(connectDb.getId(), connectDb);
        ObjectDataUtils.write(cache);
    }

    @Override
    public List<ConnectDb> findAll() {
        if (cache.isEmpty()) {
            Map<String, ConnectDb> data = ObjectDataUtils.get();
            cache.putAll(data);
        }
        List<ConnectDb> connectDbs = new ArrayList<>();
        cache.forEach((k, v) -> connectDbs.add(v));
        return connectDbs;
    }

    @Override
    public void deleteById(String id) {
        cache.remove(id);
        ObjectDataUtils.write(cache);
    }

    @Override
    public void clearAll() {
        ObjectDataUtils.write(new HashMap<>());
    }

}
