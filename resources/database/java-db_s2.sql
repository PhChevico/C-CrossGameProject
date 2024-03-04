CREATE DATABASE c_cross_team21;


DROP TABLE IF EXISTS logs_info cascade ;
DROP TABLE IF EXISTS game_statistics cascade ;
DROP SEQUENCE IF EXISTS seq_game_id;


CREATE TABLE IF NOT EXISTS logs_info (
                                        name VARCHAR(15) constraint pk_logs_info primary key,
                                        password VARCHAR(25)
);


CREATE TABLE IF NOT EXISTS game_statistics(
                                        id SERIAL PRIMARY KEY , --game id
                                        name VARCHAR(15) constraint fk_name_logs_info references logs_info(name), --winner name
                                        time_played TIMESTAMP NOT NULL, --total time played
                                        total_turns_p1 NUMERIC(2),--input the total turn used by the winner
                                        total_turns_p2 NUMERIC(2)--input the total turn used by the winner
);






