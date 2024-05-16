package be.kdg.ccross.view.selectlevelScreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.selectModeScreen.SelectModePresenter;
import be.kdg.ccross.view.selectModeScreen.SelectModeView;
import be.kdg.ccross.view.singleplayerscreen.SinglePlayerPresenter;
import be.kdg.ccross.view.singleplayerscreen.SinglePlayerView;
import javafx.stage.Screen;

public class SelectLevelPresenter {
    private GameSession model;
    private SelectLevelView view;

    public SelectLevelPresenter(GameSession model, SelectLevelView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getMediumButton().setOnMouseEntered(e -> view.getMediumButton().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getMediumButton().setOnMouseExited(e -> view.getMediumButton().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getHardButton().setOnMouseEntered(e -> view.getHardButton().setStyle("-fx-background-color: #f60202; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getHardButton().setOnMouseExited(e -> view.getHardButton().setStyle("-fx-background-color: rgba(255,55,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getGoBack().setOnMouseEntered(e -> view.getGoBack().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getGoBack().setOnMouseExited(e -> view.getGoBack().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getMediumButton().setOnAction(e -> setSinglePlayerView(false));
        view.getHardButton().setOnAction(e -> setSinglePlayerView(true));
        view.getGoBack().setOnAction(e -> setSelectModeView());
    }

    private void setSinglePlayerView(boolean isHard) {
        model.setLevel(isHard);  // Assuming true for hard and false for medium

        SinglePlayerView singlePlayerView = new SinglePlayerView();
        view.getScene().setRoot(singlePlayerView);
        SinglePlayerPresenter singlePlayerPresenter = new SinglePlayerPresenter(model, singlePlayerView);
        singlePlayerView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - singlePlayerView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - singlePlayerView.getScene().getWindow().getHeight() / 2;
        singlePlayerView.getScene().getWindow().setX(centerX);
        singlePlayerView.getScene().getWindow().setY(centerY);
    }

    private void setSelectModeView() {
        SelectModeView selectModeView = new SelectModeView();
        view.getScene().setRoot(selectModeView);
        SelectModePresenter selectModePresenter = new SelectModePresenter(model, selectModeView);
        selectModeView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - selectModeView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - selectModeView.getScene().getWindow().getHeight() / 2;
        selectModeView.getScene().getWindow().setX(centerX);
        selectModeView.getScene().getWindow().setY(centerY);
    }
}
