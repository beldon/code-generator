package me.beldon;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.beldon.module.generate.ui.GenerateView;
import me.beldon.module.window.service.impl.WindowService;
import me.beldon.module.window.ui.MainView;
import me.beldon.util.SpringContextUtil;
import org.controlsfx.control.Notifications;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

import java.awt.*;

@SpringBootApplication
public class GenerateApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
//        launchApp(GenerateApplication.class, MainView.class, args);
        launchApp(GenerateApplication.class, GenerateView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SystemTray.isSupported();//支持系統托盤
        super.start(stage);
        getStage().setOnCloseRequest(event -> {
            Platform.setImplicitExit(false); //禁止退出
            Platform.runLater(getStage()::hide); //最小化到托盘
            WindowService windowService = SpringContextUtil.getApplicationContext().getBean(WindowService.class);
            windowService.enableTray();
        });
    }
}
