package me.beldon.module.generate.service;


import me.beldon.module.database.bean.GenerateData;

/**
 * 代码生成Service
 * Created by Beldon.
 * Copyright (c) 2016/10/14, All Rights Reserved.
 * http://beldon.me
 */
public interface MySqlGenerateService {

    /**
     * 生成代码
     *
     * @param generateData 生成的数据信息
     * @throws Exception
     */
    void generate(GenerateData generateData) throws Exception;
}
