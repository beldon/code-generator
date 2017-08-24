package me.beldon.module.window.service;

import de.felixroske.jfxsupport.AbstractFxmlView;
import javafx.stage.Stage;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/15, All Rights Reserved.
 * http://beldon.me
 */
public interface WindowService {
    /**
     * 托盘
     */
    void enableTray();

    /**
     * 显示dialog
     *
     * @param dialogView
     * @param title
     * @param width
     * @param height
     */
    void showDialog(final Class<? extends AbstractFxmlView> dialogView, String title, double width, double height);

    /**
     * 获取dialog
     *
     * @param viewClass
     * @return
     */
    Stage getDialogStage(Class viewClass);

    void closeDialog(Class viewClass);
}
