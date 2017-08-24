package me.beldon.module.generate.ui;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.beldon.module.generate.domain.ConnectDb;
import me.beldon.module.generate.service.ConnectDbService;
import me.beldon.module.window.service.WindowService;
import org.controlsfx.control.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/21, All Rights Reserved.
 * http://beldon.me
 */
@FXMLController
public class NewConnectionController implements Initializable {
    private final static Logger logger = LoggerFactory.getLogger(NewConnectionController.class);
//    com.mysql.jdbc.Driver
    public static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";

    public ChoiceBox<String> dbTypeChoice;
    public ChoiceBox<String> encodingChoice;
    public TextField hostField;
    public TextField portField;
    public TextField userNameField;
    public TextField schemaField;
    public PasswordField passwordField;
    public TextField nameField;

    @Autowired
    private GenerateController generateController;



    @Autowired
    private ConnectDbService connectDbService;

    @Autowired
    private WindowService windowService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbTypeChoice.setValue("MySQL");
        encodingChoice.setValue("utf8");
        portField.setText("3306");
        hostField.setText("localhost");
        userNameField.setText("root");
    }

    public void testConnection(ActionEvent actionEvent) {
        if (checkConnect()) {
            Notifications.create().title("测试连接").text("连接成功！").position(Pos.BASELINE_CENTER).showInformation();
        }
    }


    public void saveConnection(ActionEvent actionEvent) {
        if (checkConnect()) {
            ConnectDb connectDb = new ConnectDb();
            String name = nameField.getText();
            if (StringUtils.isEmpty(name)) {
                name = schemaField.getText();
            }
            connectDb.setName(name);
            connectDb.setType(dbTypeChoice.getValue());
            connectDb.setHost(hostField.getText());
            connectDb.setPort(portField.getText());
            connectDb.setUser(userNameField.getText());
            connectDb.setPass(passwordField.getText());
            connectDb.setSchema(schemaField.getText());
            connectDb.setEncoding(encodingChoice.getValue());
            connectDbService.save(connectDb);
            Notifications.create().title("测试连接").text("保存成功").position(Pos.BASELINE_CENTER).showInformation();
            generateController.loadLeftDBTree();
            windowService.closeDialog(NewConnectionView.class);
        }
    }

    public boolean checkConnect() {
        String host = hostField.getText();
        if (StringUtils.isEmpty(host)) {
            Notifications.create().title("测试连接").text("主机名或IP地址不能为空").position(Pos.BASELINE_CENTER).showWarning();
            return false;
        }

        String portText = portField.getText();
        if (StringUtils.isEmpty(portText)) {
            Notifications.create().title("测试连接").text("端口不能为空").position(Pos.BASELINE_CENTER).showWarning();
            return false;
        }
        try {
            Integer.parseInt(portText.trim());
        } catch (Exception e) {
            Notifications.create().title("测试连接").text("端口只能为纯数字").position(Pos.BASELINE_CENTER).showWarning();
            return false;
        }

        String username = userNameField.getText();
        if (StringUtils.isEmpty(username)) {
            Notifications.create().title("测试连接").text("密码不能为空").position(Pos.BASELINE_CENTER).showWarning();
            return false;
        }

        String schema = schemaField.getText();
        if (StringUtils.isEmpty(schema)) {
            Notifications.create().title("测试连接").text("Schema/数据库不能为空").position(Pos.BASELINE_CENTER).showWarning();
            return false;
        }

        String password = passwordField.getText();
        try {
            Class.forName(DRIVER_MYSQL);
            String url = "jdbc:mysql://" + host + ":" + portText + "/" + schema + "?serverTimezone=UTC&characterEncoding=" + encodingChoice.getValue();
            DriverManager.getConnection(url, username, password);
            return true;
        } catch (Exception e) {
            logger.info("Database connect error",e);
            Notifications.create().title("测试连接").text("连接失败！").position(Pos.BASELINE_CENTER).showWarning();
        }

        return false;
    }
}
