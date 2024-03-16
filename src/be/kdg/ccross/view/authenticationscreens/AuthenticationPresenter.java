package be.kdg.ccross.view.authenticatonscreens;


import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.authenticationscreens.AuthenticationView;
import be.kdg.ccross.view.registerscreens.RegisterPresenter;
import be.kdg.ccross.view.registerscreens.RegisterView;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AuthenticationPresenter {
    be.kdg.ccross.view.authenticationscreens.AuthenticationView view;
    GameSession model;
    public AuthenticationPresenter(GameSession model, AuthenticationView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }
    private void addEventHandlers(){
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);
        view.getCancel().setOnAction(this::closeApplication);

        view.getcreateacc().setOnAction(actionEvent -> {
            setRegisterView();
        } );

        view.getLog_in().setOnAction(actionEvent -> {
            String username = view.getUsernamefield().getText();
            String password = view.getPasswordField().getText();
//            model.registerUser(username, password); here we have to creat a method for login as well
            welcome(null);
        });


    }

    private void setHomeScreenView(){
        // ONE WAY TO DO IT BUT MISSING THE OWNERSHIP OF THE NEW STAGE
//        BoardView boardView = new BoardView();
//        BoardPresenter boardPresenter = new BoardPresenter(model, boardView);
//        Stage stage = new Stage();
//        stage.setScene(new Scene(boardView));
//        stage.show();
//        ((Stage)view.getScene().getWindow()).close();

        HomeScreenView homeScreenView = new HomeScreenView();
        view.getScene().setRoot(homeScreenView);
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(model, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - homeScreenView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - homeScreenView.getScene().getWindow().getHeight() / 2;
        homeScreenView.getScene().getWindow().setX(centerX);
        homeScreenView.getScene().getWindow().setY(centerY);

    }
    private void setRegisterView(){
        RegisterView registerView = new RegisterView();
        view.getScene().setRoot(registerView);
        RegisterPresenter registerPresenter = new RegisterPresenter(model, registerView);
        registerView.getScene().getWindow();
//        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - registerView.getScene().getWindow().getWidth() / 2;
//        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - registerView.getScene().getWindow().getHeight() / 2;
//        registerView.getScene().getWindow().setX(centerX);
//        registerView.getScene().getWindow().setY(centerY);


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
    private void welcome(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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


}
