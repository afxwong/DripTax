package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import resources.Enums;

public class ConfigScreen {

    private TextField namefield;
    private Button togamescreen;
    private ComboBox<Enums.Difficulty> difficultyComboBox;
    private final ObservableList<Enums.Difficulty> difficulties;
    private int width;
    private int height;

    public TextField getNamefield() {
        return this.namefield;
    }

    public Button getTogamescreen() {
        return this.togamescreen;
    }

    public ComboBox<Enums.Difficulty> getDifficultyComboBox() {
        return this.difficultyComboBox;
    }

    public ConfigScreen(int width, int height) {
        this.difficulties = FXCollections.observableArrayList(Enums.Difficulty.values());
        this.width = width;
        this.height = height;
    }

    public Scene getConfigScene() {
        StackPane top = new StackPane();
        VBox center = new VBox();
        top.setPadding(new Insets(50, 0, 0, 0));
        top.setAlignment(Pos.CENTER);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(50);

        VBox namepane = new VBox();
        Label namelabel = new Label("Name:");
        this.namefield = new TextField();
        this.namefield.setMaxWidth(200);
        namepane.getChildren().addAll(namelabel, this.namefield);
        namepane.setAlignment(Pos.CENTER);
        this.namefield.setPromptText("ENTER YOUR NAME");
        this.difficultyComboBox = new ComboBox<>();
        this.difficultyComboBox.setPromptText("CHOOSE A DIFFICULTY");
        this.difficultyComboBox.setItems(difficulties);
        this.togamescreen = new Button("Begin");
        center.getChildren().addAll(namepane, this.difficultyComboBox, this.togamescreen);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);

        return new Scene(root, this.width, this.height);
    }
}
