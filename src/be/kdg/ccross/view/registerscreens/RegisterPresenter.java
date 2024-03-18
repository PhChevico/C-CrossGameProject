package be.kdg.ccross.view.registerscreens;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import be.kdg.ccross.view.authenticationscreens.AuthenticationPresenter;
import be.kdg.ccross.view.authenticationscreens.AuthenticationView;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class RegisterPresenter {
    RegisterView view;
    GameSession model;
    public RegisterPresenter(GameSession model, RegisterView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }
    private void addEventHandlers(){
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);
        view.getCancel().setOnAction(this::closeApplication);
        view.getLog_in().setOnAction(actionEvent ->{
            setAuthenticationView();
        });
        view.getRegister().setOnAction(actionEvent -> {
            String userName = view.getNameField().getText();
            String password = view.getPasswordField().getText();
            String confirmPasswd = view.getConfirmFiled().getText();
            model.registerUser(userName,password,confirmPasswd);
            registerAlert(null);
        });
    }
    private void setAuthenticationView(){
        AuthenticationView authenticationView = new AuthenticationView();
        view.getScene().setRoot(authenticationView);
        AuthenticationPresenter authenticationPresenter = new AuthenticationPresenter(model, authenticationView);
    }

    private void setHomeScreenView(){

        HomeScreenView homeScreenView = new HomeScreenView();
        view.getScene().setRoot(homeScreenView);
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(model, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();

    }

    private void closeApplication(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
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
    private void registerAlert(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Register successfully");
        model.setUsername(view.getNameField().getText());
        alert.setContentText("Welcome " + model.getUsername());
        ButtonType next = new ButtonType("Next !");
        alert.getButtonTypes().setAll(next);
        // using setOnAction instead of addEventFilter
        alert.setOnCloseRequest(e -> {
            setHomeScreenView();
        });

        alert.showAndWait();
    }
}
