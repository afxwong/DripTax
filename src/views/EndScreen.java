package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class EndScreen {

    private int width;
    private int height;
    private final Label endtext;
    private Button exitbutton;
    private Button playagainbutton;


    public Button getExitbutton() {
        return exitbutton;
    }

    public Button getPlayagainbutton() {
        return playagainbutton;
    }

    public EndScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.endtext = new Label("GAME OVER");
        this.exitbutton = new Button("Exit Game");
        this.playagainbutton = new Button("Play Again");
    }

    public Scene getEndScene() {
        StackPane top = new StackPane();
        FlowPane center = new FlowPane();
        top.setPadding(new Insets(50, 0, 0, 0));
        top.setAlignment(Pos.CENTER);
        center.setAlignment(Pos.CENTER);

        this.endtext.setFont(new Font("Georgia", 50));
        this.endtext.setAlignment(Pos.CENTER);
        top.getChildren().add(this.endtext);

        center.getChildren().addAll(this.exitbutton, this.playagainbutton);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);

        return new Scene(root, this.width, this.height);
    }
}
