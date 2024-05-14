package be.kdg.ccross.view.instructionscreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InstructionsPresenter {
    private  InstructionsView view;
    private GameSession session;

    public InstructionsPresenter(GameSession session,InstructionsView view) {
        this.session = session;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBackButton().setOnAction(event -> goBack());

        view.getBackButton().setOnMouseEntered(e -> view.getBackButton().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getBackButton().setOnMouseExited(e -> view.getBackButton().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);
    }

    private void goBack() {
        HomeScreenView homeScreenView = new HomeScreenView();
        Scene scene = view.getScene();
        scene.setRoot(homeScreenView);
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(true);
        stage.setTitle("C-Cross");
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(session, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();
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
            ((Stage) view.getScene().getWindow()).close();
        } else {
            event.consume();
        }
    }
}

