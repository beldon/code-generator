package me.beldon.module.database.service;


import me.beldon.module.database.bean.Type;

/**
 * Created by Beldon.
 * Copyright (c) 2016/10/18, All Rights Reserved.
 * http://beldon.me
 */
public interface IMySqlTypeService {
    Type getType(String mysqlType);
}
