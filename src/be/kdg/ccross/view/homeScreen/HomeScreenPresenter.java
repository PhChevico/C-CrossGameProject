package be.kdg.ccross.view.homeScreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.instructionscreen.InstructionsPresenter;
import be.kdg.ccross.view.instructionscreen.InstructionsView;
import be.kdg.ccross.view.leaderboardScreen.LeaderboardPresenter;
import be.kdg.ccross.view.leaderboardScreen.LeaderboardView;
import be.kdg.ccross.view.selectModeScreen.SelectModePresenter;
import be.kdg.ccross.view.selectModeScreen.SelectModeView;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeScreenPresenter {

    private GameSession model;
    private HomeScreenView view;


    public HomeScreenPresenter(GameSession model, HomeScreenView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    void addEventHandlers() {

        view.getPlay().setOnAction(actionEvent -> setSelectMode());

        view.getLeaderboard().setOnAction(actionEvent -> setLeaderboardView());

        view.getInstructions().setOnAction(actionEvent -> setInstructionView());

        view.getPlay().setOnMouseEntered(e -> view.getPlay().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getPlay().setOnMouseExited(e -> view.getPlay().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getInstructions().setOnMouseEntered(e -> view.getInstructions().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getInstructions().setOnMouseExited(e -> view.getInstructions().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getLeaderboard().setOnMouseEntered(e -> view.getLeaderboard().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getLeaderboard().setOnMouseExited(e -> view.getLeaderboard().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getExit().setOnMouseEntered(e -> view.getExit().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getExit().setOnMouseExited(e -> view.getExit().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getExit().setOnAction(this::closeApplication);
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);


    }

    void setSelectMode(){

        SelectModeView selectModeView = new SelectModeView();
        Scene scene = view.getScene();
        scene.setRoot(selectModeView);
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(true);
        stage.setTitle("C-Cross");
        SelectModePresenter selectModePresenter = new SelectModePresenter(model, selectModeView);
        selectModeView.getScene().getWindow().sizeToScene();

    }
    void setLeaderboardView(){

        LeaderboardView leaderboardView = new LeaderboardView();
        view.getScene().setRoot(leaderboardView);
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(model,leaderboardView);
        leaderboardView.getScene().getWindow().sizeToScene();
    }
    void setInstructionView(){

        InstructionsView instructionsView = new InstructionsView();
        view.getScene().setRoot(instructionsView);
        InstructionsPresenter instructionsPresenter = new InstructionsPresenter(model,instructionsView);
        instructionsView.getScene().getWindow().sizeToScene();
    }


    void closeApplication(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/C-Cross.png")); // To add an icon
        alert.setHeaderText("You are about to quit the game!");
        alert.setContentText("Do you really want to leave?");
        alert.setTitle("Bye ;-; !");
        alert.getButtonTypes().clear();
        ButtonType yes = new ButtonType("YES");
        ButtonType no = new ButtonType("NO");
        alert.getButtonTypes().addAll(yes, no);
        alert.showAndWait();
        if (alert.getResult().equals(yes)) {
            ((Stage)view.getScene().getWindow()).close();
        } else {
            event.consume();
        }
    }



}