package me.beldon.module.database.bean;


import me.beldon.module.database.entity.mysql.Columns;

/**
 * Created by Beldon.
 * Copyright (c) 2016/10/18, All Rights Reserved.
 * http://beldon.me
 */
public class ColumnData {
    private String name;
    private Type type;
    private Columns column;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Columns getColumn() {
        return column;
    }

    public void setColumn(Columns column) {
        this.column = column;
    }
}
