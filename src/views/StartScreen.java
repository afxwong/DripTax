package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class StartScreen {


    private int width;
    private int height;
    private final Label gamename;
    private final Label prompt;

    public StartScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.gamename = new Label("Team 33: DripTax");
        this.prompt = new Label("PRESS ENTER TO CONTINUE");
    }

    public Scene getStartScene() {
        StackPane top = new StackPane();
        FlowPane center = new FlowPane();
        top.setPadding(new Insets(50, 0, 0, 0));
        top.setAlignment(Pos.CENTER);
        center.setAlignment(Pos.CENTER);

        this.gamename.setFont(new Font("Georgia", 50));
        this.gamename.setAlignment(Pos.CENTER);
        top.getChildren().add(this.gamename);

        this.prompt.setFont(new Font("Georgia", 25));
        center.getChildren().add(this.prompt);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);

        return new Scene(root, this.width, this.height);
    }
}
