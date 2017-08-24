package me.beldon;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Platform;
import javafx.stage.Stage;
import me.beldon.module.generate.ui.GenerateView;
import me.beldon.module.window.service.impl.WindowServiceImpl;
import me.beldon.util.SpringContextUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class GenerateApplication extends AbstractJavaFxApplicationSupport {
    public static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    public static void main(String[] args) {
        launchApp(GenerateApplication.class, GenerateView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SystemTray.isSupported();//支持系統托盤
        super.start(stage);
        getStage().setOnCloseRequest(event -> {
            Platform.setImplicitExit(false); //禁止退出
            Platform.runLater(getStage()::hide); //最小化到托盘
            WindowServiceImpl windowServiceImpl = SpringContextUtil.getApplicationContext().getBean(WindowServiceImpl.class);
            windowServiceImpl.enableTray();
        });
    }



    static {
        try {
            Class.forName(DRIVER_MYSQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
