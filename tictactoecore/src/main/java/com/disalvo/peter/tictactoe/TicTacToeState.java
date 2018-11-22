package com.disalvo.peter.tictactoe;

public interface TicTacToeState {

    TicTacToeState start();

    TicTacToeState stop();

    TicTacToeState ensureCanPlay();

    Turn nextTurn(Turn turn);
}
