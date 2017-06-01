package me.beldon.module.database.service.impl;

import me.beldon.module.database.entity.mysql.Columns;
import me.beldon.module.database.entity.mysql.Schemata;
import me.beldon.module.database.entity.mysql.Tables;
import me.beldon.module.database.service.IMySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库辅助类
 * Created by Beldon.
 * Copyright (c) 2016/10/14, All Rights Reserved.
 * http://beldon.me
 */
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@Service
public class MySqlService implements IMySqlService {

    private JdbcTemplate jdbcTemplate;

    public List<Schemata> getAllSchemata() {
        return jdbcTemplate.query("select * from SCHEMATA;", BeanPropertyRowMapper.newInstance(Schemata.class));
    }

    public List<Tables> getAllSchemataTables(String schemata) {
        return jdbcTemplate.query("select * from TABLES WHERE TABLE_SCHEMA = ?;", new Object[]{schemata}, BeanPropertyRowMapper.newInstance(Tables.class));
    }

    public List<Columns> getAllSchemataColumns(String schemata) {
        return jdbcTemplate.query("select * from `COLUMNS` WHERE TABLE_SCHEMA = ?;", new Object[]{schemata}, BeanPropertyRowMapper.newInstance(Columns.class));
    }

    public List<Columns> getAllSchemataTableColumns(String schemata, String table) {
        return jdbcTemplate.query("select * from `COLUMNS` WHERE TABLE_SCHEMA = ? AND `TABLE_NAME` = ?;", new Object[]{schemata, table}, BeanPropertyRowMapper.newInstance(Columns.class));
    }

    public List<Columns> getAllSchemataTableColumns(Tables table) {
        return getAllSchemataTableColumns(table.getTableSchema(),table.getTableName());
    }

    @Override
    public void switchJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
