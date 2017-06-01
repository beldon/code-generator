package me.beldon.config;

import freemarker.template.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/21, All Rights Reserved.
 * http://beldon.me
 */
@Configuration
public class CoreConfig {

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(new Version("2.3.0"));
        try {
            configuration.setDirectoryForTemplateLoading(new File("./res/"));
            configuration.setDefaultEncoding("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuration;
    }
}
