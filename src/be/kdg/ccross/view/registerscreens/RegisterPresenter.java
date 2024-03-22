package be.kdg.ccross.view.registerscreens;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import be.kdg.ccross.view.authenticationscreens.AuthenticationPresenter;
import be.kdg.ccross.view.authenticationscreens.AuthenticationView;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

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
            if (!model.getAuthentication().registerUser(userName ,password)){
                userError(null);
            } else if (!Objects.equals(password, confirmPasswd)) {
                passwordError(null);
            }else {
                model.getAuthentication().registerUser(userName ,password);
                registerAlert(null);
            }

        });
    }
    private void setAuthenticationView(){
        AuthenticationView authenticationView = new AuthenticationView();
        view.getScene().setRoot(authenticationView);
        AuthenticationPresenter authenticationPresenter = new
                AuthenticationPresenter(model, authenticationView);

    }

    private void setHomeScreenView(){

        HomeScreenView homeScreenView = new HomeScreenView();
        Scene scene = view.getScene();
        scene.setRoot(homeScreenView);
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(true);
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(model, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();

    }
    void setRegisterView(){
        RegisterView registerView = new RegisterView();
        view.getScene().setRoot(registerView);
        RegisterPresenter registerPresenter = new RegisterPresenter(model, registerView);
        registerView.getScene().getWindow();

    }

    private void closeApplication(Event event) {
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
    private void registerAlert(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/C-Cross.png")); // To add an icon
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
    private void userError(Event event){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/C-Cross.png"));
        alert.setHeaderText("User already existing");
        alert.setContentText("Try another name");
        ButtonType retry = new ButtonType("Try again");
        alert.getButtonTypes().setAll(retry);
        alert.setOnCloseRequest(e -> {
            setRegisterView();
        });
        alert.showAndWait();
    }
    private void passwordError(Event event){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/C-Cross.png"));
        alert.setHeaderText("Password are not matching");
        ButtonType retry = new ButtonType("Try again");
        alert.getButtonTypes().setAll(retry);
        alert.setOnCloseRequest(e -> {
            setRegisterView();
        });
        alert.showAndWait();
    }
}
