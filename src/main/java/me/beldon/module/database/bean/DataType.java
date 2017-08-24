package me.beldon.module.database.bean;

/**
 * Created by Beldon.
 * Copyright (c) 2016/10/18, All Rights Reserved.
 * http://beldon.me
 */
public class DataType {
    private String mysqlType;
    private String jdbcType;
    private String javaType;
    private String javaFullType;

    public DataType() {
    }

    public DataType(String mysqlType, String jdbcType, String javaType, String javaFullType) {
        this.mysqlType = mysqlType;
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.javaFullType = javaFullType;
    }

    public String getMysqlType() {
        return mysqlType;
    }

    public void setMysqlType(String mysqlType) {
        this.mysqlType = mysqlType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaFullType() {
        return javaFullType;
    }

    public void setJavaFullType(String javaFullType) {
        this.javaFullType = javaFullType;
    }
}
