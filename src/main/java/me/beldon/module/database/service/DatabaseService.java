package me.beldon.module.database.service;

import me.beldon.module.generate.domain.ConnectDb;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/26, All Rights Reserved.
 * http://beldon.me
 */
public interface DatabaseService {

  void switchConnect(ConnectDb connectDb);

  JdbcTemplate currentJdbcTemplate();
}
