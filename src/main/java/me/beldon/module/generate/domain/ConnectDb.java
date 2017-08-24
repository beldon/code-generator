package me.beldon.module.generate.domain;

import java.io.Serializable;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/21, All Rights Reserved.
 * http://beldon.me
 */
public class ConnectDb implements Serializable{
    private String id;

    /**
     * 保存名称
     */
    private String name;

    /**
     * 数据库类型
     */
    private String type;

    /**
     * 主机
     */
    private String host;

    /**
     * 数据库端口
     */
    private String port;

    /**
     * 数据库用户
     */
    private String user;

    /**
     * 数据密码
     */
    private String pass;

    /**
     * 数据库schema
     */
    private String schema;

    /**
     * 连接编码
     */
    private String encoding;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
