package com.disalvo.peter.tictactoe;

public interface TicTacToeState {

    TicTacToeState start();

    TicTacToeState stop();

    TicTacToeState ensureCanPlay();

    TicTacToeState next(DecidesNextState decidesNextState);

    interface DecidesNextState {
        TicTacToeState next(TicTacToeState state);
    }
}
