package me.beldon.module.window.ui;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import me.beldon.GenerateApplication;
import me.beldon.module.generate.ui.GenerateView;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/15, All Rights Reserved.
 * http://beldon.me
 */
@FXMLController
public class MainController {

    public void showGenerate(ActionEvent actionEvent) {
        GenerateApplication.showView(GenerateView.class);
    }


}
