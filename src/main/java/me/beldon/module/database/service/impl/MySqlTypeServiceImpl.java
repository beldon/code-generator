package me.beldon.module.database.service.impl;

import me.beldon.module.database.bean.DataType;
import me.beldon.module.database.service.MySqlTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beldon.
 * Copyright (c) 2016/10/18, All Rights Reserved.
 * http://beldon.me
 */
@Service
public class MySqlTypeServiceImpl implements MySqlTypeService {
    private Map<String, DataType> types = new HashMap<String, DataType>();

    @PostConstruct
    public void init() {
        add(new DataType("smallint", "INTEGER", "Integer", "java.lang.Integer"));
        add(new DataType("mediumint", "INTEGER", "Integer", "java.lang.Integer"));
        add(new DataType("int", "BIGINT", "Long", "java.lang.Long"));
        add(new DataType("integer", "INTEGER", "Integer", "java.lang.Integer"));
        add(new DataType("bigint", "BIGINT", "Long", "java.lang.Long"));
        add(new DataType("varchar", "VARCHAR", "String", "java.lang.String"));
        add(new DataType("text", "VARCHAR", "String", "java.lang.String"));
        add(new DataType("datetime", "TIMESTAMP", "Date", "java.util.Date"));
        add(new DataType("date", "TIMESTAMP", "Date", "java.util.Date"));
        add(new DataType("tinyint", "INTEGER", "Integer", "java.lang.Integer"));
        add(new DataType("bit", "BIT", "Boolean", "java.lang.Boolean"));
        add(new DataType("longtext", "VARCHAR", "String", "java.lang.String"));
        add(new DataType("decimal", "Double", "Double", "java.lang.Double"));
        add(new DataType("char", "CHAR", "String", "java.lang.String"));
    }

    private void add(DataType dataType) {
        types.put(dataType.getMysqlType(), dataType);
    }

    public DataType getType(String mysqlType) {
        if (StringUtils.hasText(mysqlType) && types.containsKey(mysqlType)) {
            return types.get(mysqlType);
        }
        return new DataType();
    }
}
