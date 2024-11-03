package com.example.hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.beans.EventHandler;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        NewTest controller = loader.getController();
        controller.setText(10);
        controller.setTextLabel();
        controller.showWordsNumber();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            SolveTest solveTest = new SolveTest(event.getCode(), controller.getTextLabel());
            solveTest.solve();
            controller.updateWPMLabel(solveTest.result());
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}