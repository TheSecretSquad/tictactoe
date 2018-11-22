package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;

class GameEndConditionStalemate implements GameEndCondition {
    @Override
    public TicTacToeState nextState(TicTacToeState currentState) {
        return currentState.stop();
    }

    @Override
    public GameEndCondition announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        stateAnnouncer.stalemate(mark, position);
        return this;
    }

    @Override
    public GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia) {
        return null;
    }
}
