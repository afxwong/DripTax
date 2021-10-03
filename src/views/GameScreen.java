package views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreen {

    private int width;
    private int height;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getGameScene() throws FileNotFoundException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Pane root = new Pane();
        root.setBackground(new Background(new BackgroundImage(
                new Image("resources/mapbackground.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        )));

        return new Scene(root, this.width, this.height);
    }
}
