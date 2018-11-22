package com.disalvo.peter.tictactoe;

public interface GameEndCondition {

    TicTacToeState nextState(TicTacToeState currentState);

    GameEndCondition announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position);

    GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia);

    interface StateAnnouncer {
        StateAnnouncer continuePlay(Mark mark, Position position);

        StateAnnouncer winningPlay(Mark mark, Position position);

        StateAnnouncer stalemate(Mark mark, Position position);
    }
}
