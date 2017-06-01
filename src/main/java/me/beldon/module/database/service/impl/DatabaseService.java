package me.beldon.module.database.service.impl;

import me.beldon.module.database.service.IDatabaseService;
import me.beldon.module.database.service.IMySqlService;
import me.beldon.module.generate.domain.ConnectDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/26, All Rights Reserved.
 * http://beldon.me
 */
@Service
public class DatabaseService implements IDatabaseService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IMySqlService mySqlService;

    @Override
    public void switchConnect(ConnectDb connectDb) {
        String url = "jdbc:mysql://" + connectDb.getHost() + ":" + connectDb.getPort() + "/information_schema";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(connectDb.getUser());
        dataSource.setPassword(connectDb.getPass());
        jdbcTemplate = new JdbcTemplate(dataSource);
        mySqlService.switchJdbcTemplate(jdbcTemplate);
    }

    @Override
    public JdbcTemplate currentJdbcTemplate() {
        return jdbcTemplate;
    }
}
