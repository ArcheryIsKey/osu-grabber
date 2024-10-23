package com.archery.osugrabber.controller;

import com.archery.osugrabber.enums.GameMode;
import com.archery.osugrabber.osu.Osu;
import com.archery.osugrabber.osu.Play;
import com.archery.osugrabber.osu.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private double x = 0,y = 0;

    @FXML
    private TextField apiKey;
    @FXML
    private TextField username;
    @FXML
    private HBox h_box;
    @FXML
    private TabPane tabPane;
    @FXML
    private AnchorPane backgroundAnchor;
    @FXML
    private Stage stage;
    @FXML
    private TableView<Play> osu_standard_table;
    @FXML
    private TableView<Play> osu_mania_table;
    @FXML
    private TableView<Play> osu_taiko_table;
    @FXML
    private TableView<Play> osu_catch_table;

    private String osuUsername;

    private Osu osu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*
        * TODO: Find a better way to do this.
        */

        TextField newTextField = username;
        h_box.getChildren().remove(username); // Scuffed

        TabPane newTabPane = tabPane;
        h_box.getChildren().remove(tabPane); // Scuffed

        backgroundAnchor.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        backgroundAnchor.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        apiKey.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                osu = new Osu(apiKey.getText());
                if(!osu.isValidKey) return;
                h_box.getChildren().remove(apiKey);
                h_box.getChildren().add(username);
                apiKey.setEditable(false);
            }
        });

        username.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                try {

                    User user = osu.getUser(username.getText());

                    // TODO: Make this programmatic?

                    ObservableList<Play> standard = FXCollections.observableArrayList(osu.getBest(user, 5, GameMode.STANDARD));
                    ObservableList<Play> mania = FXCollections.observableArrayList(osu.getBest(user, 5, GameMode.MANIA));
                    ObservableList<Play> taiko = FXCollections.observableArrayList(osu.getBest(user, 5, GameMode.TAIKO));
                    ObservableList<Play> ctb = FXCollections.observableArrayList(osu.getBest(user, 5, GameMode.CATCH));

                    getTables().get(0).setItems(standard);
                    getTables().get(1).setItems(mania);
                    getTables().get(2).setItems(taiko);
                    getTables().get(3).setItems(ctb);

                    h_box.getChildren().remove(username);
                    h_box.getChildren().add(newTabPane);

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

                // fuck this is ugly

                osu_standard_table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("full_name"));
                osu_standard_table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("pp"));

                osu_mania_table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("full_name"));
                osu_mania_table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("pp"));

                osu_taiko_table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("full_name"));
                osu_taiko_table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("pp"));

                osu_catch_table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("full_name"));
                osu_catch_table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("pp"));



            }
        });
    }

    public List<TableView> getTables() {
        List<TableView> list = new ArrayList<>();
        list.add(osu_standard_table);
        list.add(osu_mania_table);
        list.add(osu_taiko_table);
        list.add(osu_catch_table);
        return list;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getUsername() {
        return osuUsername;
    }

    public void setUsername(String username) {
        this.osuUsername = username;
    }
}