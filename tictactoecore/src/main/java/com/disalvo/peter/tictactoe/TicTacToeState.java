package com.disalvo.peter.tictactoe;

interface TicTacToeState {

    TicTacToeState start();

    TicTacToeState stop();

    TicTacToeState won();

    TicTacToeState stalemate();

    PlayState play();

    Turn nextTurn(Turn turn);

    TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position);

    interface PlayState {
        TicTacToeState nextState(GameEndCondition boardCondition);

        interface GameEndCondition {

            TicTacToeState nextState(TicTacToeState startingState);
        }
    }

    interface StateAnnouncer {
        StateAnnouncer continuePlay(Mark mark, Position position);

        StateAnnouncer winningPlay(Mark mark, Position position);

        StateAnnouncer stalemate(Mark mark, Position position);
    }
}
