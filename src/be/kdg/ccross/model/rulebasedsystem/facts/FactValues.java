package be.kdg.ccross.model.rulebasedsystem.facts;

public enum FactValues { //value to understand what is happening
    START_NEW_WINNING_PATH, //used to start a new winning path from the AI.
    CONTINUE_PATH, //used to continue the winning path, as long the player doesn't own a zone on ur winning path
    BLOCK_PLAYER_FROM_WINNING; //used to avoid player from winning by checking his zones claimed;

}

