package be.kdg.ccross.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private int idGame;
    private int idPlayer;
    private Connection connection;

    public Database() {
        connect();
    }
    private void connect() {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:your_database_connection_string");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}