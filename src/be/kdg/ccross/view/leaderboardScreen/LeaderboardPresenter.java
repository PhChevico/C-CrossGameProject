package be.kdg.ccross.view.leaderboardScreen;

import be.kdg.ccross.model.Database;
import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.model.PlayerStatistics;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import be.kdg.ccross.view.piechartscreen.PieChartPresenter;
import be.kdg.ccross.view.piechartscreen.PieChartView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderboardPresenter {
    private String sortBy;
    private GameSession model;
    private LeaderboardView view;
    private Database database;

    public LeaderboardPresenter(GameSession model, LeaderboardView view) {
        this.model = model;
        this.view = view;
        this.database = new Database(); // Create an instance of the Database class
        sortBy = "Wins";
        addEventHandlers();
        updateView(); // Call updateView to initially populate the leaderboard
    }

    private void addEventHandlers() {
        view.getGoBack().setOnAction(actionEvent -> setHomeScreenView());

        view.getGamesPlayed().setOnMouseClicked(mouseEvent -> {
            sortBy = "Games Played";
            updateView();
        });

        view.getPlayerName().setOnMouseClicked(mouseEvent -> {
            sortBy = "Player Name";
            updateView();
        });

        view.getWins().setOnMouseClicked(mouseEvent -> {
            sortBy = "Wins";
            updateView();
        });

        view.getLosses().setOnMouseClicked(mouseEvent -> {
            sortBy = "Losses";
            updateView();
        });

        view.getPercentageWon().setOnMouseClicked(mouseEvent -> {
            sortBy = "% Games Won";
            updateView();
        });

        view.getAvgMoves().setOnMouseClicked(mouseEvent -> {
            sortBy = "Avg Moves";
            updateView();
        });

        view.getAvgDuration().setOnMouseClicked(mouseEvent -> {
            sortBy = "Avg Duration";
            updateView();
        });

        view.getGoBack().setOnMouseEntered(e -> view.getGoBack().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getGoBack().setOnMouseExited(e -> view.getGoBack().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
    }

    public void updateView() {
        // Fetch player statistics from the database
        List<PlayerStatistics> playerStatisticsList = database.getPlayerStatistics();

        // Filter out players with at least 1 game played
        List<PlayerStatistics> filteredList = playerStatisticsList.stream()
                .filter(stats -> stats.getGamesPlayed() > 0)
                .collect(Collectors.toList());

        // Sort the filtered list based on the selected criterion
        boolean ascending = false;
        List<PlayerStatistics> sortedList = filteredList.stream()
                .sorted(getComparator(sortBy, ascending))
                .collect(Collectors.toList());

        // Format the sorted data for the view
        List<String[]> formattedData = sortedList.stream()
                .map(stats -> new String[]{
                        String.valueOf(sortedList.indexOf(stats) + 1),  // Use the index of the sorted list for rank
                        stats.getPlayerName(),
                        String.valueOf(stats.getGamesPlayed()),
                        String.valueOf(stats.getWins()),
                        String.valueOf(stats.getLosses()),
                        String.format("%.2f", (stats.getLosses()==0)&&(stats.getWins()==0)||((stats.getLosses()>0)&&stats.getWins()==0) ? 0 :
                                stats.getLosses()==0 ?  100 : (double) (stats.getWins() / stats.getLosses())),
                        String.format("%.2f", stats.getAvgMoves()),
                        String.format("%.2f", stats.getAvgDuration())
                })
                .collect(Collectors.toList());

        view.updateLeaderboard(formattedData, this::setPieChartPlayer);
    }



    private Comparator<PlayerStatistics> getComparator(String sortBy, boolean ascending) {
        Comparator<PlayerStatistics> comparator;

        switch (sortBy) {
            case "Games Played":
                comparator = Comparator.comparingInt(PlayerStatistics::getGamesPlayed);
                break;
            case "Player Name":
                comparator = Comparator.comparing(PlayerStatistics::getPlayerName);
                break;
            case "Wins":
                comparator = Comparator.comparingInt(PlayerStatistics::getWins);
                break;
            case "Losses":
                comparator = Comparator.comparingInt(PlayerStatistics::getLosses);
                break;
            case "% Games Won":
                comparator = Comparator.comparingDouble(stats -> (double) stats.getWins() / stats.getLosses());
                break;
            case "Avg Moves":
                comparator = Comparator.comparingDouble(PlayerStatistics::getAvgMoves);
                break;
            case "Avg Duration":
                comparator = Comparator.comparingDouble(PlayerStatistics::getAvgDuration);
                break;
            default:
                comparator = Comparator.comparingInt(PlayerStatistics::getWins);
                break;
        }

        return ascending ? comparator : comparator.reversed();
    }

    public void setPieChartPlayer(String[] data) {
        String playerName = data[1];
        int wins = Integer.parseInt(data[3]);
        int losses = Integer.parseInt(data[4]);

        PieChartView pieChartView = new PieChartView();
        Scene scene = view.getScene();
        scene.setRoot(pieChartView);
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(true);
        stage.setTitle("Player Statistics - " + playerName);
        new PieChartPresenter(model, pieChartView, playerName, wins, losses);
        pieChartView.getScene().getWindow().sizeToScene();
    }

    private void setHomeScreenView() {
        HomeScreenView homeScreenView = new HomeScreenView();
        Scene scene = view.getScene();
        scene.setRoot(homeScreenView);
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(true);
        stage.setTitle("C-Cross");
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(model, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();
    }
}
