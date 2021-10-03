package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class StartScreen {

    public StartScreen() { }

    public Scene getStartScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("@../../../resources/startscreen.fxml"));
        Parent root = loader.load();
        return new Scene(root);
    }
}
