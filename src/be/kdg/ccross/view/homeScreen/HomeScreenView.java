package be.kdg.ccross.view.homeScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HomeScreenView extends VBox {

    Button play;
    Button load;
    Button leaderboard;
    Button exit;


    public HomeScreenView(){
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes(){
        play = new Button("Play");
        load = new Button("Load");
        leaderboard = new Button("LeaderBoard");
        exit = new Button("Exit");
    }

    private void layoutNodes() {
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

    public Button getPlay() {
        return play;
    }

    public Button getLoad() {
        return load;
    }

    public Button getLeaderboard() {
        return leaderboard;
    }

    public Button getExit() {
        return exit;
    }
}
