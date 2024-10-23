package com.archery.osugrabber.controller;

import com.archery.osugrabber.enums.GameMode;
import com.archery.osugrabber.osu.Osu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
                    osu.getBest(osu.getUser(username.getText()), 5, GameMode.MANIA).forEach(play -> System.out.println(play.getFull_name() + " | " + play.getPp() + "\n"));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
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