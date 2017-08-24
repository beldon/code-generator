package me.beldon.module.window.service.impl;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.GUIState;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.beldon.GenerateApplication;
import me.beldon.module.window.service.WindowService;
import me.beldon.util.SpringContextUtil;
import org.controlsfx.control.Notifications;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/15, All Rights Reserved.
 * http://beldon.me
 */
@Service
public class WindowServiceImpl implements WindowService {
    private TrayIcon trayIcon;
    private boolean first = true;

    private Map<Class, Stage> dialogs = new HashMap<>();

    @Override
    public void enableTray() {
        if (SystemTray.isSupported()) {
            try {
                if (first) {
                    trayInit();
                    SystemTray.getSystemTray().add(trayIcon);
                    trayIcon.displayMessage("退出提示", "已最小化到托盘.", TrayIcon.MessageType.NONE);
                    first = false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Notifications.create().title("系统错误").text("系统不支持托盘").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(5)).showError();
        }
    }

    @Override
    public void showDialog(Class<? extends AbstractFxmlView> dialogView, String title, double width, double height) {
        Stage dialogStage;
        if (dialogs.containsKey(dialogView)) {
            dialogStage = dialogs.get(dialogView);
        }else{
            final AbstractFxmlView view = SpringContextUtil.getApplicationContext().getBean(dialogView);
            Scene scene = new Scene(view.getView());
            dialogStage = new Stage();
            dialogStage.setScene(scene);
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(GUIState.getStage());
            dialogStage.setMaximized(false);
            dialogStage.setResizable(false);
            dialogs.put(dialogView, dialogStage);
        }
        dialogStage.setTitle(title);
        dialogStage.setHeight(height);
        dialogStage.setWidth(width);
        dialogStage.show();
    }

    @Override
    public Stage getDialogStage(Class viewClass) {
        return dialogs.get(viewClass);
    }

    @Override
    public void closeDialog(Class viewClass) {
        Stage stage = getDialogStage(viewClass);
        if (stage != null) {
            stage.close();
        }
    }

    private void trayInit() {
        Stage stage = GenerateApplication.getStage();
        PopupMenu popupMenu = new PopupMenu();
        MenuItem openItem = new MenuItem("Show");
        MenuItem hideItem = new MenuItem("Small最小化");
        MenuItem quitItem = new MenuItem("Exit");

        ActionListener acl = e -> {
            MenuItem item = (MenuItem) e.getSource();
            Platform.setImplicitExit(false); //多次使用显示和隐藏设置false
            if (item.getLabel().equals("Exit")) {
                SystemTray.getSystemTray().remove(trayIcon);
                Platform.exit();
                return;
            }
            if (item.getLabel().equals("Show")) {
                stage.centerOnScreen();
                Platform.runLater(() -> stage.show());
            }
            if (item.getLabel().equals("Small")) {
                Platform.runLater(() -> stage.hide());
            }

        };

        //双击事件方法
        MouseListener mouseListener = new MouseListener() {
            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
                Platform.setImplicitExit(false); //多次使用显示和隐藏设置false
                if (e.getClickCount() == 2) {
                    if (stage.isShowing()) {
                        Platform.runLater(() -> stage.hide());
                    } else {
                        Platform.runLater(() -> stage.show());
                    }
                }
            }
        };

        openItem.addActionListener(acl);
        quitItem.addActionListener(acl);
        hideItem.addActionListener(acl);

        popupMenu.add(openItem);
        popupMenu.add(hideItem);
        popupMenu.add(quitItem);

        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/image/sysTray.png"));
            trayIcon = new TrayIcon(image, "tools", popupMenu);
            trayIcon.addMouseListener(mouseListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
