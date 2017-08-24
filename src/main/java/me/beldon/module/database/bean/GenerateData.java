package me.beldon.module.database.bean;

import me.beldon.module.database.entity.mysql.Tables;
import me.beldon.module.template.bean.TemplateDetails;
import me.beldon.module.template.bean.TemplateFtl;

/**
 * 代码生成的基本信息
 * Created by Beldon.
 * Copyright (c) 2016/10/14, All Rights Reserved.
 * http://beldon.me
 */
public class GenerateData {
    /**
     * 作者
     */
    private String author = "";

    /**
     * 作者url
     */
    private String url = "";

    /**
     * 代码生成的根路径
     */
    private String basePath;

    /**
     * 基本的包
     */
    private String basePackage;

    /**
     * 模板详情
     */
    private TemplateDetails templateDetails;

    /**
     * 模板
     */
    private TemplateFtl template;

    /**
     * 实体类名称
     */
    private String domainName;

    /**
     * 模板路径
     */
    private String templatePath;

    /**
     * 表
     */
    private Tables table;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public TemplateDetails getTemplateDetails() {
        return templateDetails;
    }

    public void setTemplateDetails(TemplateDetails templateDetails) {
        this.templateDetails = templateDetails;
    }

    public TemplateFtl getTemplate() {
        return template;
    }

    public void setTemplate(TemplateFtl template) {
        this.template = template;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
