package me.beldon.module.generate.service.impl;


import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import me.beldon.module.database.bean.ColumnData;
import me.beldon.module.database.bean.GenerateData;
import me.beldon.module.database.bean.Type;
import me.beldon.module.database.entity.mysql.Columns;
import me.beldon.module.database.service.IMySqlService;
import me.beldon.module.database.service.IMySqlTypeService;
import me.beldon.module.generate.service.IMySqlGenerateService;
import me.beldon.module.template.bean.TemplateDetails;
import me.beldon.module.template.bean.TemplateFtl;
import me.beldon.util.SSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;


/**
 * 代码生成Service
 * Created by Beldon.
 * Copyright (c) 2016/10/14, All Rights Reserved.
 * http://beldon.me
 */
@SuppressWarnings("Duplicates")
@Service
public class MySqlGenerateService implements IMySqlGenerateService {

    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private IMySqlService mySqlService;
    @Autowired
    private IMySqlTypeService mySqlTypeService;


    @Override
    public void generate(GenerateData generateData) throws Exception {
        List<Columns> columns = mySqlService.getAllSchemataTableColumns(generateData.getTable());
        String basePath = generateData.getBasePath(); //基本路径
        TemplateFtl templateFtl = generateData.getTemplate();

        TemplateDetails templateDetails = generateData.getTemplateDetails();

        String templatePath = "/templates/" + templateDetails.getPath() + File.separator + templateFtl.getFileName();
        String dirPath; //生成的文件目录
        String basePackage = generateData.getBasePackage();
        String pk = "";
        if ("java".equals(generateData.getTemplate().getType())) {
            pk = basePackage + templateFtl.getTargetPackage();
            dirPath = basePath + templateFtl.getBasePath() + File.separator + pk.replaceAll("\\.", "\\\\") + "\\";
        } else {
            dirPath = basePath + templateFtl.getTargetPath();
        }

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String domainName = generateData.getDomainName();
        List<ColumnData> columnDatas = new ArrayList<>();

        ColumnData primaryData = new ColumnData();
        Set<String> importType = new LinkedHashSet<>();
        for (Columns column : columns) {
            ColumnData columnData = new ColumnData();
            columnData.setName(SSUtils.underlineToCamel2(column.getColumnName()));
            columnData.setColumn(column);
            Type type = mySqlTypeService.getType(column.getDataType());
            columnData.setType(type);
            columnDatas.add(columnData);
            if (type != null && StringUtils.hasText(type.getJavaFullType())) {
                String javaFullType = type.getJavaFullType();
                if (StringUtils.hasText(javaFullType) && !javaFullType.contains("java.lang")) {
                    importType.add(javaFullType);
                }
            }

            if ("PRI".equals(column.getColumnKey())) {
                primaryData = columnData;
            }

        }
        //组织数据
        Map<String, Object> data = new HashMap<>();
        data.put("package", pk);
        data.put("table", generateData.getTable());
        data.put("domainName", domainName); //实体类名称
        data.put("data", generateData); //生成的数据信息
        data.put("url", generateData.getUrl()); //生成的数据信息
        data.put("columnDatas", columnDatas); //所有字段信息
        data.put("importType", importType); //所需要导入的包
        data.put("primaryData", primaryData); //主键
        data.put("templateFtl", templateFtl); //模板信息

        templateFtl.setTargetFileName(replace(templateFtl.getTargetFileName(), data));
        data.put("className", templateFtl.getTargetFileName().replaceAll("[.][^.]+$", "")); //生成的类的名称

        String filePath = dirPath + "" + templateFtl.getTargetFileName();
        FileWriter fileWriter = new FileWriter(new File(filePath));
        Template template = configuration.getTemplate(templatePath);
        template.process(data, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * freemarker内容转换
     *
     * @param templateContent 文本内容
     * @param data            data内容
     * @return
     */
    private String replace(String templateContent, Map<String, Object> data) {
        if (StringUtils.isEmpty(templateContent)) {
            return "";
        }
        Configuration cfg = new Configuration();
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("templateContent", templateContent);
        cfg.setTemplateLoader(stringLoader);
        try {
            Template template = cfg.getTemplate("templateContent", "utf-8");

            StringWriter writer = new StringWriter();
            try {
                template.process(data, writer);
                return writer.toString();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return templateContent;
    }

}
