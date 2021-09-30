package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ConfigScreen {

    public ConfigScreen() { }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("@../../../resources/configscreen.fxml"));
        Parent root = loader.load();
        return new Scene(root);
    }
}
