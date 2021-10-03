package views;

import javafx.scene.Scene;
import javafx.scene.control.Label;

public class GameScreen {

    private Label gamename;
    private Label prompt;

    public GameScreen() {
        this.gamename = new Label("INSERT NAME");
        this.prompt = new Label("PRESS ENTER TO CONTINUE");
    }

//    public Scene getGameScene() {
//        return new Scene()
//    }
}
