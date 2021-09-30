package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class StartScreen {

    public StartScreen() { }

    public Scene getStartScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("@../../../resources/startscreen.fxml"));
        Parent root = loader.load();
        return new Scene(root);
    }
}
