package be.kdg.ccross.view.homeScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HomeScreenView extends VBox {

    private Button play;
    private Button load;
    private Button leaderboard;
    private Button exit;


    public HomeScreenView(){
        initialiseNodes();
        layoutNodes();
    }

    void initialiseNodes(){
        play = new Button("Play");
        load = new Button("Load");
        leaderboard = new Button("LeaderBoard");
        exit = new Button("Exit");
    }

    void layoutNodes() {
        super.getChildren().addAll(play, load, leaderboard, exit);

        setAlignment(Pos.CENTER);
        play.setMinSize(200, 20);
        load.setMinSize(200, 20);
        leaderboard.setMinSize(200, 20);
        exit.setMinSize(200, 20);
        setSpacing(20);


        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.jpg", false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        setMinHeight(600);
        setMinWidth(300);

    }

    Button getPlay() {
        return play;
    }

    Button getLoad() {
        return load;
    }

    Button getLeaderboard() {
        return leaderboard;
    }

    Button getExit() {
        return exit;
    }
}
