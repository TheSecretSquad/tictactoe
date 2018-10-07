package com.disalvo.peter;

import static com.disalvo.peter.GameEndEvaluation.GameEndCondition;

interface TicTacToeState {

    TicTacToeState start();

    TicTacToeState stop();

    TicTacToeState won();

    TicTacToeState stalemate();

    PlayState play();

    Turn nextTurn(Turn turn);

    TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position);

    interface PlayState {
        TicTacToeState nextState(GameEndCondition gameEndCondition);
    }

    interface StateAnnouncer {
        StateAnnouncer continuePlay(Mark mark, Position position);

        StateAnnouncer winningPlay(Mark mark, Position position);

        StateAnnouncer stalemate(Mark mark, Position position);
    }
}
