package be.kdg.ccross.view.instructionscreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class InstructionsView extends VBox {
    private Label instructionsTitle;
    private TextFlow instructionsTextFlow;
    private Button backButton;

    public InstructionsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.png", false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        instructionsTitle = new Label("Instructions");

        String instructionsText =
                "In C-Cross, you want to connect two opposing sides of the game board with your own tiles — " +
                        "but before you can place a large C-shaped tile on the board, you need to place three pawns in the area " +
                        "where this tile would go. When you do conquer an area with three of your pawns and cover it with one " +
                        "of your tiles, you return those pawns to your supply. \n" +
                        "If you connect two opposing sides with your C-tiles, you win. Alternatively, if your opponent can't play, you also win!";

        Text textNode = new Text(instructionsText);
        textNode.setFont(Font.font("Arial", 15));
        textNode.setFill(Color.rgb(79, 46, 0));

        instructionsTextFlow = new TextFlow(textNode);
        instructionsTextFlow.setMaxWidth(600);

        backButton = new Button("Go Back");
    }

    private void layoutNodes() {
        instructionsTitle.setFont(Font.font("Login", FontWeight.BOLD, FontPosture.ITALIC, 30));
        instructionsTitle.setStyle("-fx-text-fill: rgb(79,46,0)");

        backButton.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");

        // Create a rectangle with 25% opacity
        Rectangle backgroundRectangle = new Rectangle();
        backgroundRectangle.setWidth(650); // Set the width of the rectangle
        backgroundRectangle.setHeight(150); // Set the height of the rectangle (adjust as needed)
        backgroundRectangle.setFill(Color.rgb(255, 153, 10, 0.40)); // Set the color with 25% opacity

        // Use StackPane to layer the rectangle behind the text
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundRectangle, instructionsTextFlow);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(20)); // Optional padding around text

        this.getChildren().addAll(instructionsTitle, stackPane, backButton);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(25);
    }

    Button getBackButton() {
        return backButton;
    }
}
