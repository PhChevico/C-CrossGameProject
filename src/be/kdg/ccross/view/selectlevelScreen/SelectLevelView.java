package be.kdg.ccross.view.selectlevelScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class SelectLevelView extends VBox {
    private Button mediumButton;
    private Button hardButton;
    private Button goBack;
    private Label selectLevel;

    public SelectLevelView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        selectLevel = new Label("SELECT LEVEL");
        mediumButton = new Button("Medium");
        hardButton = new Button("Hard");
        goBack = new Button("Go back to mode selection");

        // Apply styles
        selectLevel.setStyle("-fx-text-fill: #4f2e00; -fx-font-weight: bold");
        mediumButton.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        hardButton.setStyle("-fx-background-color: rgba(255,55,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        goBack.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
    }

    private void layoutNodes() {
        super.getChildren().addAll(selectLevel, mediumButton, hardButton, goBack);

        setAlignment(Pos.CENTER);
        mediumButton.setMinSize(200, 20);
        hardButton.setMinSize(200, 20);
        goBack.setMinSize(200, 20);
        setSpacing(20);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        setMinHeight(600);
        setMinWidth(600);
    }

    public Button getMediumButton() {
        return mediumButton;
    }

    public Button getHardButton() {
        return hardButton;
    }

    public Button getGoBack() {
        return goBack;
    }
}
