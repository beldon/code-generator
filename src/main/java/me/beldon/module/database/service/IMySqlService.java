package me.beldon.module.database.service;


import me.beldon.module.database.entity.mysql.Columns;
import me.beldon.module.database.entity.mysql.Schemata;
import me.beldon.module.database.entity.mysql.Tables;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 数据库辅助类
 * Created by Beldon.
 * Copyright (c) 2016/10/14, All Rights Reserved.
 * http://beldon.me
 */
public interface IMySqlService {
    /**
     * 获取所有Schemata
     *
     * @return
     */
    List<Schemata> getAllSchemata();

    /**
     * 获取指定schemata所有Table
     *
     * @param schemata 指定schemata
     * @return
     */
    List<Tables> getAllSchemataTables(String schemata);

    /**
     * 获取指定schemata 所有 columns
     *
     * @param schemata 指定schemata
     * @return
     */
    List<Columns> getAllSchemataColumns(String schemata);

    /**
     * 获取指定schemata下指定指定table 所有 columns
     *
     * @param schemata 指定schemata
     * @param table    指定table
     * @return
     */
    List<Columns> getAllSchemataTableColumns(String schemata, String table);

    /**
     * 获取指定table下指定指定table 所有 columns
     *
     * @param table 指定table
     * @return
     */
    List<Columns> getAllSchemataTableColumns(Tables table);

    void switchJdbcTemplate(JdbcTemplate jdbcTemplate);
}
