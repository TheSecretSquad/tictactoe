package com.disalvo.peter;

interface TicTacToeState {

    TicTacToeState start();

    TicTacToeState stop();

    PlayState play();

    Turn nextTurn(Turn turn);

    void announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position);

    interface PlayState {
        TicTacToeState nextState(WinningEvaluation winningEvaluation, Board board);
    }

    interface StateAnnouncer {
        StateAnnouncer continuePlay(TicTacToeState ticTacToeState, Mark mark, Position position);

        StateAnnouncer winningPlay(TicTacToeState ticTacToeState, Mark mark, Position position);

        StateAnnouncer stalemate(TicTacToeState ticTacToeState, Mark mark, Position position);
    }
}
