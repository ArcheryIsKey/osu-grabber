package com.archery.osugrabber;

import com.archery.osugrabber.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("apiKeyEntrance.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        Controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setOpacity(1);
        stage.setTitle("osu! profile grabber");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}