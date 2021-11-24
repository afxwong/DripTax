package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import resources.GameConfig;

public class EndScreen {

    private int width;
    private int height;
    private Label endtext;
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

    public void changeEndText(String s) {
        this.endtext.setText(s);
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

        VBox infobox = new VBox();
        infobox.setPadding(new Insets(10, 10, 10, 10));
        Label enemieskilled = new Label("Enemies Killed: " + GameConfig.getEnemiesKilled());
        Label timeelapsed = new Label("Time Elapsed (ms): "
                + (GameConfig.getEndtime() - GameConfig.getStarttime()));
        infobox.getChildren().addAll(enemieskilled, timeelapsed);
        center.getChildren().addAll(infobox, this.exitbutton, this.playagainbutton);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);

        return new Scene(root, this.width, this.height);
    }
}
