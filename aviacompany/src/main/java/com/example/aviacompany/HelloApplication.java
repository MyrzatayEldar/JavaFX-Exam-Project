package com.example.aviacompany;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        setPrimaryStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Начальная страница.");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("4585523.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return HelloApplication.stg;
    }
    private void setPrimaryStage(Stage stage) {
        HelloApplication.stg = stage;
    }
    public static void main(String[] args) {
        launch();
    }
}