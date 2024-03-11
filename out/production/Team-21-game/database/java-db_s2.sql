CREATE DATABASE c_cross_team21;


DROP TABLE IF EXISTS logs_info cascade ;
DROP TABLE IF EXISTS game_statistics cascade ;
DROP SEQUENCE IF EXISTS seq_game_id;


CREATE TABLE IF NOT EXISTS logs_info (
                                        name VARCHAR(15),
                                        username VARCHAR(15) constraint pk_username primary key,
                                        password VARCHAR(25)
);

CREATE TABLE Players (
                         player_id INT PRIMARY KEY,
                         player_name VARCHAR(255)
);

CREATE TABLE Games (
                       game_id INT PRIMARY KEY,
                       start_time TIMESTAMP,
                       end_time TIMESTAMP,
                       winner_id INT,
                       total_play_time TIME
);

CREATE TABLE Moves (
                       move_id INT PRIMARY KEY,
                       game_id INT,
                       player_id INT,
                       move_time TIME,
                       FOREIGN KEY (game_id) REFERENCES Games(game_id),
                       FOREIGN KEY (player_id) REFERENCES Players(player_id)
);






