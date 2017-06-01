package me.beldon.module.template.bean;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 模板基本信息类
 * Created by Beldon.
 * Copyright (c)  2017/5/27, All Rights Reserved.
 * http://beldon.me
 */
public class TemplateDetails {
    /**
     * 模板名字
     */
    private String name;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 作者链接
     */
    private String url;

    /**
     * 模板描述
     */
    private String description;

    private String path;

    /**
     * 模板文件
     */
    private List<TemplateFtl> templates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TemplateFtl> getTemplates() {
        return templates;
    }

    public void setTemplates(List<TemplateFtl> templates) {
        this.templates = templates;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return StringUtils.hasText(name) ? name : path;
    }
}
