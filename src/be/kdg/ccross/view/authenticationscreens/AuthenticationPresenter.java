package be.kdg.ccross.view.authenticationscreens;


import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.authenticationscreens.AuthenticationView;
import be.kdg.ccross.view.registerscreens.RegisterPresenter;
import be.kdg.ccross.view.registerscreens.RegisterView;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AuthenticationPresenter {
    be.kdg.ccross.view.authenticationscreens.AuthenticationView view;
    private GameSession model;
    public AuthenticationPresenter(GameSession model, AuthenticationView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }
    void addEventHandlers(){
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);
        view.getCancel().setOnAction(this::closeApplication);

        view.getcreateacc().setOnAction(actionEvent -> {
            setRegisterView();
        } );

        view.getLog_in().setOnAction(actionEvent -> {
            String username = view.getUsernamefield().getText();
            String password = view.getPasswordField().getText();
            if ((username == null) && (password==null)) {
                userError(null);

            }else if (model.getAuthentication().isLoginCorrect(username,password)){
                model.getDatabase().updateLastAccessTime(username);
                welcome(null);
            }else {
                userError(null);
            }

        });
        view.getLog_in().setOnMouseEntered(e -> view.getLog_in().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getLog_in().setOnMouseExited(e -> view.getLog_in().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getcreateacc().setOnMouseEntered(e -> view.getcreateacc().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getcreateacc().setOnMouseExited(e -> view.getcreateacc().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getCancel().setOnMouseEntered(e -> view.getCancel().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getCancel().setOnMouseExited(e -> view.getCancel().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
    }

    void setHomeScreenView(){

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
    private void setAuthenticationView(){
        AuthenticationView authenticationView = new AuthenticationView();
        view.getScene().setRoot(authenticationView);
        AuthenticationPresenter authenticationPresenter = new
                AuthenticationPresenter(model, authenticationView);

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
    void welcome(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/C-Cross.png"));
        alert.setHeaderText("Log in successfully");
        model.setUsername(view.getUsernamefield().getText());
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
        alert.setHeaderText("User or password are incorrect");
        alert.setContentText("Try spell it correctly");
        ButtonType retry = new ButtonType("Try again");
        alert.getButtonTypes().setAll(retry);
        alert.setOnCloseRequest(e -> {
            setAuthenticationView();
        });
        alert.showAndWait();
    }


}
