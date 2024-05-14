package be.kdg.ccross.view.selectModeScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class SelectModeView extends VBox {

    private Button singlePlayer;
    private Button multiPlayer;
    private Button goBack;
    private Button exit;
    private Label selectMode;

    public SelectModeView(){
        initialiseNodes();
        layoutNodes();
    }

    void initialiseNodes(){
        selectMode = new Label("SELECT MODE");
        singlePlayer = new Button("SinglePlayer Mode");
        multiPlayer = new Button("MultiPlayer Mode");
        goBack = new Button("Go back to main menu");
        exit = new Button("Exit");

        // Apply styles
        selectMode.setStyle("-fx-text-fill: #4f2e00; -fx-font-weight: bold");
        singlePlayer.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        multiPlayer.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        goBack.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        exit.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
    }

    void layoutNodes() {
        super.getChildren().addAll(selectMode,singlePlayer, multiPlayer, goBack, exit);

        setAlignment(Pos.CENTER);
        singlePlayer.setMinSize(200, 20);
        multiPlayer.setMinSize(200, 20);
        goBack.setMinSize(200, 20);
        exit.setMinSize(200, 20);
        setSpacing(20);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        setMinHeight(300);
        setMinWidth(600);
    }

    Button getSinglePlayer() {
        return singlePlayer;
    }

    Button getMultiPlayer() {
        return multiPlayer;
    }

    Button getGoBack() {
        return goBack;
    }

    Button getExit() {
        return exit;
    }
}
