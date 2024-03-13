package be.kdg.ccross.view.homeScreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.boardscreen.BoardPresenter;
import be.kdg.ccross.view.boardscreen.BoardView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreenPresenter {

    GameSession model;
    HomeScreenView view;
    public HomeScreenPresenter(GameSession model, HomeScreenView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getPlay().setOnAction(actionEvent -> {
            BoardView boardView = new BoardView();
            BoardPresenter boardPresenter = new BoardPresenter(model, boardView);

            Stage stage = new Stage();
            stage.setScene(new Scene(boardView));
            stage.show();
        });
    }

}
