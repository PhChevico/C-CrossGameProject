CREATE DATABASE c_cross_team21;
/*u should also turn number that is specific to each game*/

DROP TABLE IF EXISTS logs_info cascade ;
DROP TABLE IF EXISTS Players cascade ;
DROP TABLE IF EXISTS Games cascade ;
DROP TABLE IF EXISTS Moves cascade ;
DROP SEQUENCE IF EXISTS seq_game_id;
SELECT * FROM logs_info;
CREATE TABLE IF NOT EXISTS logs_info (
                                         username VARCHAR(15) NOT NULL PRIMARY KEY,
                                         password VARCHAR(25) NOT NULL
);
SELECT * FROM Players;
CREATE TABLE IF NOT EXISTS Players (
                                       username VARCHAR(15) NOT NULL,
                                       FOREIGN KEY (username) REFERENCES logs_info(username)
);
SELECT * FROM Games;
CREATE TABLE IF NOT EXISTS Games (
                                     game_id SERIAL PRIMARY KEY,
                                     start_time TIMESTAMP NOT NULL,
                                     end_time TIMESTAMP,
                                     winner_username VARCHAR(15), -- Use username instead of player_id
                                     total_play_time BIGINT,
                                     FOREIGN KEY (winner_username) REFERENCES logs_info(username) -- Reference the username column in logs_info
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

-- Insert sample data into logs_info table
INSERT INTO logs_info (username, password)
VALUES
    ('john123', 'password123'),
    ('alice456', 'qwerty789');

-- Insert sample data into Players table
INSERT INTO Players (username)
VALUES
    ('john123'),
    ('alice456');

-- Insert sample data into Games table
INSERT INTO Games (start_time, end_time, winner_username, total_play_time)
VALUES
    ('2024-05-01 12:00:00', '2024-05-01 13:00:00', 'john123', '1 hour'),
    ('2024-05-01 13:30:00', '2024-05-01 14:00:00', 'alice456', '30 minutes');

-- Insert sample data into Moves table
INSERT INTO Moves (game_id, username, move_time)
VALUES
    (1, 'john123', 5000), -- 5 seconds for move in game 1 by user 'john123'
    (1, 'alice456', 6000), -- 6 seconds for move in game 1 by user 'alice456'
    (2, 'alice456', 7000), -- 7 seconds for move in game 2 by user 'alice456'
    (2, 'john123', 4000); -- 4 seconds for move in game 2 by user 'john123'
-- Insert more sample data into logs_info table
INSERT INTO logs_info (name, username, password)
VALUES
    ('Bob Johnson', 'bob789', 'pass123'),
    ('Emily Brown', 'emily012', 'secure456');

-- Insert more sample data into Players table
INSERT INTO Players (username)
VALUES
    ('bob789'),
    ('emily012');

-- Insert more sample data into Games table
INSERT INTO Games (start_time, end_time, winner_username, total_play_time)
VALUES
    ('2024-05-02 10:00:00', '2024-05-02 11:30:00', 'bob789', '1 hour 30 minutes'),
    ('2024-05-03 14:00:00', '2024-05-03 15:30:00', 'emily012', '1 hour 30 minutes'),
    ('2024-05-04 09:00:00', '2024-05-04 10:30:00', 'bob789', '1 hour 30 minutes'),
    ('2024-05-05 16:00:00', '2024-05-05 17:30:00', 'bob789', '1 hour 30 minutes');


