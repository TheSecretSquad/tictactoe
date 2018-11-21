package com.disalvo.peter.tictactoe;

public interface GameEndCondition {

    TicTacToeState nextState(TicTacToeState currentState);
}
