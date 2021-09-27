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

public class StartScreen {
    private int width;
    private int height;
    private Label title;
    private Button startbtn;

    public StartScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.title = new Label("Hello");
        this.startbtn = new Button("Start");
    }

    public Scene getStartScene() {
        StackPane top = new StackPane();
        FlowPane center = new FlowPane();
        top.setPadding(new Insets(50, 0, 0, 0));
        top.setAlignment(Pos.CENTER);
        center.setAlignment(Pos.CENTER);

        this.title.setFont(new Font("Georgia", 50));
        this.title.setAlignment(Pos.CENTER);
        top.getChildren().add(this.title);

        this.startbtn.setFont(new Font("Georgia", 25));
        center.getChildren().add(this.startbtn);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);

        Scene scene = new Scene(root, this.width, this.height);
        return scene;
    }
}
