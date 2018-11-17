package com.disalvo.peter.tictactoe;

public interface PlayState {
    TicTacToeState nextState(GameEndCondition gameEndCondition);

    interface GameEndCondition {

        TicTacToeState nextState(TicTacToeState currentState);
    }
}
