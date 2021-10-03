package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class GameScreen {

    public GameScreen() { }

    public Scene getGameScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("@../../../resources/gamescreen.fxml"));
        Parent root = loader.load();
        return new Scene(root);
    }
}
