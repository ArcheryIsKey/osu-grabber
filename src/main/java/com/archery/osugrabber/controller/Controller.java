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
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableColumn<Play, String> player;

    @FXML
    private TableColumn<Play, String> title;

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
            ObservableList<Play> plays = FXCollections.observableArrayList(osu.getBest(user, 100));
            System.out.println(plays.get(1).getTitle());
            table.setItems(plays);
        } catch (IOException | InterruptedException  e) {}

        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        pp.setCellValueFactory(new PropertyValueFactory<>("pp"));


    }
}