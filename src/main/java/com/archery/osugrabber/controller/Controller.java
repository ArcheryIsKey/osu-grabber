package com.archery.osugrabber.controller;

import com.archery.osugrabber.osu.Osu;
import com.archery.osugrabber.osu.Play;
import com.archery.osugrabber.osu.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableColumn<Play, String> player;

    @FXML
    private TableColumn<Play, String> full_name;

    @FXML
    private TableColumn<Play, Double> pp;

    @FXML
    private TableView<Play> table;

    ObservableList<Play> plays = FXCollections.observableArrayList();

    Osu osu = new Osu("");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            User user = osu.getUser("archeryiskey");
            ObservableList<Play> plays = FXCollections.observableArrayList(osu.getBest(user, 1));
            table.setItems(plays);
        } catch (IOException | InterruptedException  e) { e.printStackTrace(); }

        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        full_name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        pp.setCellValueFactory(new PropertyValueFactory<>("pp"));


    }
}