package me.beldon.module.template.service;

import me.beldon.module.template.bean.TemplateDetails;

import java.util.List;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/27, All Rights Reserved.
 * http://beldon.me
 */
public interface ITemplateService {

    /**
     * 模板目录
     */
    String TEMPLATE_PATH = "res/templates";

    List<TemplateDetails> getAllTemplates();
}
