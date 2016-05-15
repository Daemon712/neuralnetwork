package ru.foobarbaz.neuralnetwork.som.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class KohonenNetworkApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/KohonenLearning.fxml"));
        Pane vBoxPane = loader.load();
        Scene scene = new Scene(vBoxPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
