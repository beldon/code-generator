package me.beldon.module.template.service.impl;

import com.alibaba.fastjson.JSON;
import me.beldon.module.template.bean.TemplateDetails;
import me.beldon.module.template.service.ITemplateService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/27, All Rights Reserved.
 * http://beldon.me
 */
@Service
public class TemplateService implements ITemplateService {
    @Override
    public List<TemplateDetails> getAllTemplates() {
        File file = new File(TEMPLATE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        List<TemplateDetails> templateDetails = new ArrayList<>();
        try {
            File[] templateDirs = file.listFiles(File::isDirectory); //模板目录
            for (File templateDir : templateDirs) {

                String templateDirName = templateDir.getName();
                File configFile = new File(templateDir, "config.json");

                BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile));
                String s;
                StringBuffer stringBuffer = new StringBuffer();
                while((s = bufferedReader.readLine())!=null){//使用readLine方法，一次读一行
                    stringBuffer.append(System.lineSeparator()+s);
                }
                bufferedReader.close();

                TemplateDetails details = JSON.parseObject(stringBuffer.toString(), TemplateDetails.class);
                details.setPath(templateDirName);
                templateDetails.add(details);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return templateDetails;
    }

    public static void main(String[] args) {
        TemplateService templateService = new TemplateService();
        List<TemplateDetails> details = templateService.getAllTemplates();
        System.out.println("a");
    }
}
