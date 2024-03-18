package be.kdg.ccross.view.leaderboardScreen;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LeaderboardView extends GridPane {
    private Label rank;
    private Label playerName;
    private Label gamesPlayed;
    private Label numberOfWins;
    private Label numberOfLoss;
    private Label percentageGamesWon;
    private Label numberOfMoves;
    private Label averageDuration;
    private Button goBack;
    public LeaderboardView(){
        initialiseNodes();
        layoutNodes();
    }
    private void initialiseNodes(){
        rank = new Label();
        playerName = new Label();
        gamesPlayed = new Label();
        numberOfWins = new Label();
        numberOfLoss = new Label();
        percentageGamesWon = new Label();
        numberOfMoves = new Label();
        averageDuration = new Label();
        goBack = new Button("Go back");
    }
    private void layoutNodes(){

    }
}
