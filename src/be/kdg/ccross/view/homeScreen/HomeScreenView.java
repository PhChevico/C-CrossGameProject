package be.kdg.ccross.view.homeScreen;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class HomeScreenView extends VBox {
    private Label cCross;

    private Button play;
    private Button instructions;
    private Button leaderboard;
    private Button exit;

    public HomeScreenView(){
        initialiseNodes();
        layoutNodes();
    }

    void initialiseNodes(){
        cCross = new Label("C-CROSS");
        play = new Button("Play");
        instructions = new Button("Instructions");
        leaderboard = new Button("LeaderBoard");
        exit = new Button("Exit");

        // Apply styles
        cCross.setStyle("-fx-text-fill: #4f2e00; -fx-font-weight: bold");
        play.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        instructions.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        leaderboard.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        exit.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
    }

    void layoutNodes() {
        super.getChildren().addAll(cCross,play, instructions, leaderboard, exit);

        setAlignment(Pos.CENTER);
        cCross.setFont(Font.font("Login", FontWeight.BOLD, FontPosture.ITALIC,50));
        play.setMinSize(200, 20);
        instructions.setMinSize(200, 20);
        leaderboard.setMinSize(200, 20);
        exit.setMinSize(200, 20);
        setSpacing(20);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.png", false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        setMinHeight(600);
        setMinWidth(600);
    }

    Button getPlay() {
        return play;
    }

    Button getInstructions() {
        return instructions;
    }

    Button getLeaderboard() {
        return leaderboard;
    }

    Button getExit() {
        return exit;
    }
}
