package me.beldon.module.database.service.impl;

import me.beldon.module.database.bean.Type;
import me.beldon.module.database.service.IMySqlTypeService;
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
public class MySqlTypeService implements IMySqlTypeService {
    private Map<String, Type> types = new HashMap<String, Type>();

    @PostConstruct
    public void init() {
        add(new Type("smallint", "INTEGER", "Integer", "java.lang.Integer"));
        add(new Type("mediumint", "INTEGER", "Integer", "java.lang.Integer"));
        add(new Type("int", "BIGINT", "Long", "java.lang.Long"));
        add(new Type("integer", "INTEGER", "Integer", "java.lang.Integer"));
        add(new Type("bigint", "BIGINT", "Long", "java.lang.Long"));
        add(new Type("varchar", "VARCHAR", "String", "java.lang.String"));
        add(new Type("text", "VARCHAR", "String", "java.lang.String"));
        add(new Type("datetime", "TIMESTAMP", "Date", "java.util.Date"));
        add(new Type("date", "TIMESTAMP", "Date", "java.util.Date"));
        add(new Type("tinyint", "INTEGER", "Integer", "java.lang.Integer"));
        add(new Type("bit", "BIT", "Boolean", "java.lang.Boolean"));
        add(new Type("longtext", "VARCHAR", "String", "java.lang.String"));
    }

    private void add(Type type) {
        types.put(type.getMysqlType(), type);
    }

    public Type getType(String mysqlType) {
        if (StringUtils.hasText(mysqlType) && types.containsKey(mysqlType)) {
            return types.get(mysqlType);
        }
        return new Type();
    }
}
