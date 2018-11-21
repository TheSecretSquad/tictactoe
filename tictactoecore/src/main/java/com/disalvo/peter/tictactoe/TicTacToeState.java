package com.disalvo.peter.tictactoe;

public interface TicTacToeState {

    TicTacToeState start();

    TicTacToeState stop();

    TicTacToeState ensureCanPlay();

    TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position);

    interface StateAnnouncer {
        StateAnnouncer continuePlay(Mark mark, Position position);

        StateAnnouncer winningPlay(Mark mark, Position position);

        StateAnnouncer stalemate(Mark mark, Position position);
    }
}
