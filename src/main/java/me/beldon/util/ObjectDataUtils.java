package me.beldon.util;

import me.beldon.module.generate.bean.ConnectDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Beldon.
 * Copyright (c)  2017/7/6, All Rights Reserved.
 * http://beldon.me
 */
public class ObjectDataUtils {
    private final static Logger logger = LoggerFactory.getLogger(ObjectDataUtils.class);
    private static String filePath = "res/data.data";

    public static void write(Map<String, ConnectDb> connectDbs) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            logger.info("Write obj file {} ", file.getName());
            out.writeObject(connectDbs);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, ConnectDb> get() {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                Map<String, ConnectDb> connectDbs = (Map<String, ConnectDb>) in.readObject();
                in.close();
                return connectDbs;
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Get backup file error", e);
            }
        }
        return new HashMap<>();
    }


    public static void main(String[] args) {
        Map<String, ConnectDb> connectDbMap = new HashMap<>();
        ConnectDb connectDb = new ConnectDb();
        connectDb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        connectDbMap.put(connectDb.getId(), connectDb);
        write(connectDbMap);
        Map<String, ConnectDb> connectDbMap2 = get();
        System.out.println(connectDbMap2);
    }
}
