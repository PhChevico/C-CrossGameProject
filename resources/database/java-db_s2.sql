CREATE DATABASE c_cross_team21;


DROP TABLE IF EXISTS logs_info cascade ;
DROP TABLE IF EXISTS Players cascade ;
DROP TABLE IF EXISTS Games cascade ;
DROP TABLE IF EXISTS Moves cascade ;
DROP SEQUENCE IF EXISTS seq_game_id;


CREATE TABLE IF NOT EXISTS logs_info (
                                        name VARCHAR(15) NOT NULL ,
                                        username VARCHAR(15) NOT NULL constraint pk_username primary key,
                                        password VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS Players (
                         player_id INT PRIMARY KEY NOT NULL ,
                         player_name VARCHAR(255)NOT NULL
);

CREATE TABLE IF NOT EXISTS Games (
                       game_id INT PRIMARY KEY NOT NULL ,
                       start_time TIMESTAMP NOT NULL ,
                       end_time TIMESTAMP NOT NULL ,
                       winner_id INT NOT NULL ,
                       total_play_time TIME NOT NULL,
                       FOREIGN KEY (winner_id) REFERENCES Players(player_id)
);

CREATE TABLE IF NOT EXISTS Moves (
                       move_id INT PRIMARY KEY NOT NULL ,
                       game_id INT NOT NULL ,
                       player_id INT NOT NULL ,
                       move_time TIME NOT NULL ,
                       FOREIGN KEY (game_id) REFERENCES Games(game_id),
                       FOREIGN KEY (player_id) REFERENCES Players(player_id)
);






