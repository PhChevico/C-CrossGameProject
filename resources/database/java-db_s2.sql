CREATE DATABASE c_cross_team21;
/*u should also turn number that is specific to each game*/

DROP TABLE IF EXISTS logs_info cascade ;
DROP TABLE IF EXISTS Players cascade ;
DROP TABLE IF EXISTS Games cascade ;
DROP TABLE IF EXISTS Moves cascade ;
DROP SEQUENCE IF EXISTS seq_game_id;

CREATE TABLE IF NOT EXISTS logs_info (
                                         name VARCHAR(15) NOT NULL,
                                         username VARCHAR(15) NOT NULL PRIMARY KEY,
                                         password VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS Players (
                                       player_id SERIAL PRIMARY KEY,
                                       username VARCHAR(15) NOT NULL,
                                       FOREIGN KEY (username) REFERENCES logs_info(username)
);
SELECT * FROM Games;
CREATE TABLE IF NOT EXISTS Games (
                                     game_id SERIAL PRIMARY KEY,
                                     start_time TIMESTAMP NOT NULL,
                                     end_time TIMESTAMP,
                                     winner_id INT,
                                     total_play_time INTERVAL,
                                     FOREIGN KEY (winner_id) REFERENCES Players(player_id)
);
SELECT * FROM Moves;
CREATE TABLE IF NOT EXISTS Moves (
                                     move_id SERIAL,
                                     game_id INT NOT NULL,
                                     username VARCHAR(15) NOT NULL,
                                     move_time BIGINT NOT NULL,
                                     FOREIGN KEY (game_id) REFERENCES Games(game_id),
                                     FOREIGN KEY (username) REFERENCES logs_info(username)
);

-- Sample data for logs_info table
INSERT INTO logs_info (name, username, password) VALUES
                                                     ('John Doe', 'john_doe', 'password123'),
                                                     ('Jane Smith', 'jane_smith', 'qwerty456');

-- Sample data for Players table

-- Sample data for Games table
INSERT INTO Games (game_id, start_time, end_time, winner_id, total_play_time) VALUES
                                                                                  (1, '2024-03-19 10:00:00', '2024-03-19 11:00:00', 1, '01:00:00'),
                                                                                  (2, '2024-03-19 12:00:00', '2024-03-19 13:30:00', 3, '01:30:00');

-- Sample data for Moves table
INSERT INTO Moves (move_id, game_id, player_id, move_time) VALUES
                                                               (1, 1, 1, '10:15:00'),
                                                               (2, 1, 2, '10:30:00'),
                                                               (3, 1, 1, '10:45:00'),
                                                               (4, 2, 3, '12:15:00'),
                                                               (5, 2, 2, '12:30:00'),
                                                               (6, 2, 3, '12:45:00');





