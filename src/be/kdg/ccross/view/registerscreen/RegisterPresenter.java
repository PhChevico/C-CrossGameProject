package be.kdg.ccross.view.registerscreen;


import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.boardscreen.BoardPresenter;
import be.kdg.ccross.view.boardscreen.BoardView;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

public class RegisterPresenter {
    RegisterView view;
    GameSession model;
    public RegisterPresenter(GameSession model, RegisterView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }
    private void addEventHandlers(){
        //view.getRegister().setOnAction(actionEvent ->  );
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);

        view.getLog_in().setOnAction(actionEvent -> {
            String username = view.getUserName().getText();
            String password = view.getPasswd().getText();

            model.registerUser(username, password);
            setHomeScreenView();


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
}
