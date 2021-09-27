package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.StartScreen;

public class Controller extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StartScreen scene = new StartScreen(600,600);
        stage.setScene(scene.getStartScene());
        stage.show();
    }
}
