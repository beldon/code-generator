package me.beldon.module.generate.ui;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import me.beldon.GenerateApplication;
import me.beldon.module.database.bean.GenerateData;
import me.beldon.module.database.entity.mysql.Tables;
import me.beldon.module.database.service.DatabaseService;
import me.beldon.module.database.service.MySqlService;
import me.beldon.module.generate.domain.ConnectDb;
import me.beldon.module.generate.service.ConnectDbService;
import me.beldon.module.generate.service.MySqlGenerateService;
import me.beldon.module.template.bean.TemplateDetails;
import me.beldon.module.template.bean.TemplateFtl;
import me.beldon.module.template.service.TemplateService;
import me.beldon.module.window.service.WindowService;
import me.beldon.util.SSUtils;
import org.controlsfx.control.Notifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/21, All Rights Reserved.
 * http://beldon.me
 */
@FXMLController
public class GenerateController implements Initializable {
    public TreeView<String> leftDBTree;
    public TextField tableNameField;
    public TextField domainObjectNameField;
    public TextField projectFolderField;
    public TextField authorTextFile;
    public TextField targetPackage;
    public TextField modelTargetPackage;
    public TextField daoTargetPackage;
    public TextField daoPrefix;
    public TextField daoSuffix;
    public TextField serviceTargetPackage;
    public TextField servicePrefix;
    public TextField serviceSuffix;
    public TextField urlTextFile;
    public CheckBox generatePage;
    public CheckBox generatePojo;
    public CheckBox generateDao;
    public CheckBox generateService;
    public ChoiceBox templateChoiceBox;
    @FXML
    private Label connectionLabel;
    @FXML
    private Label configsLabel;

    private Tables currentTables; //当前Tables

    @Autowired
    private WindowService windowService;

    @Autowired
    private ConnectDbService connectDbService;

    @Autowired
    private MySqlService mySqlService;
    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private MySqlGenerateService mySqlGenerateService;

    @Autowired
    private TemplateService templateService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView dbImage = new ImageView("/icons/computer.png");
        dbImage.setFitHeight(40);
        dbImage.setFitWidth(40);
        connectionLabel.setGraphic(dbImage);
//        ImageView configImage = new ImageView("/icons/config-list.png");
//        configImage.setFitHeight(40);
//        configImage.setFitWidth(40);
//        configsLabel.setGraphic(configImage);

        Stage stage = GenerateApplication.getStage();
        stage.setMaximized(true);

        //
        initDBTree();

        loadTemplate();
    }

    private void initDBTree() {
        leftDBTree.setShowRoot(false);
        leftDBTree.setRoot(new TreeItem<>());
        Callback<TreeView<String>, TreeCell<String>> defaultCellFactory = TextFieldTreeCell.forTreeView();
        leftDBTree.setCellFactory((TreeView<String> tv) -> {
            TreeCell<String> cell = defaultCellFactory.call(tv);
            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                int level = leftDBTree.getTreeItemLevel(cell.getTreeItem());
                TreeCell<String> treeCell = (TreeCell<String>) event.getSource();
                TreeItem<String> treeItem = treeCell.getTreeItem();
                if (level == 1) {
                    final ContextMenu contextMenu = new ContextMenu();
                    MenuItem item1 = new MenuItem("关闭连接");
                    item1.setOnAction(event1 -> treeItem.getChildren().clear()); //关闭链接
                    MenuItem item2 = new MenuItem("删除连接");
                    item2.setOnAction(event1 -> {
                        System.out.println("删除连接");
                        ConnectDb connectDb = (ConnectDb) treeItem.getGraphic().getUserData();
                        if (connectDb != null && StringUtils.hasText(connectDb.getId())) {
                            connectDbService.deleteById(connectDb.getId());
                            loadLeftDBTree();
                        }
                    });
                    contextMenu.getItems().addAll(item1, item2);
                    cell.setContextMenu(contextMenu);
                }
                if (event.getClickCount() == 2) { //双击
                    if (treeItem == null) return;
                    treeItem.setExpanded(true);
                    if (level == 1) {
                        ConnectDb connectDb = (ConnectDb) treeItem.getGraphic().getUserData(); //load表数据
                        databaseService.switchConnect(connectDb); //切換
                        List<Tables> tables = mySqlService.getAllSchemataTables(connectDb.getSchema());
                        ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
                        children.clear();
                        for (Tables table : tables) {
                            TreeItem<String> newTreeItem = new TreeItem<>();
                            ImageView imageView = new ImageView("icons/table.png");
                            imageView.setFitHeight(16);
                            imageView.setFitWidth(16);
                            newTreeItem.setGraphic(imageView);
                            newTreeItem.setValue(table.getTableName());
                            newTreeItem.getGraphic().setUserData(table);
                            children.add(newTreeItem);
                        }
                    } else if (level == 2) { // left DB tree level3
                        String tableName = treeCell.getTreeItem().getValue();
                        tableNameField.setText(tableName);
                        domainObjectNameField.setText(SSUtils.underlineToCamel(tableName)); //实体类名称
                        ConnectDb connectDb = (ConnectDb) treeItem.getParent().getGraphic().getUserData();
                        databaseService.switchConnect(connectDb); //切換
                        currentTables = (Tables) treeItem.getGraphic().getUserData();
//                        List<Columns> columns = mySqlService.getAllSchemataTableColumns(connectDb.getSchema(), tableName);
                    }
                }
            });
            return cell;
        });

        loadLeftDBTree();
    }

    /**
     * Connect点击事件
     *
     * @param mouseEvent
     */
    public void connection(MouseEvent mouseEvent) {
        windowService.showDialog(NewConnectionView.class, "新建连接", 400, 500);
    }

    /**
     * 加载数据
     */
    public void loadLeftDBTree() {
        TreeItem rootTreeItem = leftDBTree.getRoot();
        rootTreeItem.getChildren().clear();
        List<ConnectDb> list = connectDbService.findAll();
        if (list != null) {
            for (ConnectDb connectDb : list) {
                TreeItem<String> treeItem = new TreeItem<>();
                treeItem.setValue(connectDb.getName());
                ImageView dbImage = new ImageView("icons/computer.png");
                dbImage.setFitHeight(16);
                dbImage.setFitWidth(16);
                dbImage.setUserData(connectDb);
                treeItem.setGraphic(dbImage);
                rootTreeItem.getChildren().add(treeItem);
            }
        }
    }

    public void chooseProjectFolder(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedFolder = directoryChooser.showDialog(GenerateApplication.getStage());
        if (selectedFolder != null) {
            projectFolderField.setText(selectedFolder.getAbsolutePath());
        }
    }

    /**
     * 代码生成
     * @param actionEvent
     */
    public void generateCode(ActionEvent actionEvent) {
        if (currentTables == null) {
            Notifications.create().title("生成提示").text("请选择要生成的数据表").position(Pos.BASELINE_CENTER).showWarning();
            return;
        }

        String basePath = projectFolderField.getText();
        if (StringUtils.isEmpty(basePath)) {
            Notifications.create().title("生成提示").text("请选择项目路径").position(Pos.BASELINE_CENTER).showWarning();
            return;
        }

        GenerateData generateData = new GenerateData();
        generateData.setAuthor(authorTextFile.getText());
        generateData.setUrl(urlTextFile.getText());

        generateData.setBasePath(basePath);
        generateData.setBasePackage(targetPackage.getText());
        generateData.setDomainName(domainObjectNameField.getText());

        try {
            TemplateDetails templateDetails = (TemplateDetails) templateChoiceBox.getValue();
            if (templateDetails == null) {
                return;
            }

            generateData.setTemplateDetails(templateDetails);
            generateData.setTable(currentTables);

            List<TemplateFtl> templateFtls = templateDetails.getTemplates();
            for (TemplateFtl templateFtl : templateFtls) {
                TemplateFtl temp = new TemplateFtl();
                BeanUtils.copyProperties(templateFtl,temp);
                generateData.setTemplate(temp);
                mySqlGenerateService.generate(generateData);
            }

            Notifications.create().title("生成提示").text("生成成功！").position(Pos.BASELINE_CENTER).showInformation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 刷新模板
     * @param actionEvent
     */
    public void refreshTemplate(ActionEvent actionEvent) {
        loadTemplate();
    }

    /**
     * 重新加载模板
     */
    private void loadTemplate() {
        List<TemplateDetails> templateDetails = templateService.getAllTemplates();
        ObservableList<TemplateDetails> items = FXCollections.observableArrayList(templateDetails);
        templateChoiceBox.setItems(items);
        if (items.size() > 0) {
            templateChoiceBox.setValue(items.get(0));
        }
    }
}
